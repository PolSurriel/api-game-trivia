package com.example.trivia.service.impl;

import com.example.trivia.client.TriviaAPIClient;
import com.example.trivia.entity.AnswerEntity;
import com.example.trivia.entity.GameEntity;
import com.example.trivia.entity.GameQuestionEntity;
import com.example.trivia.entity.QuestionEntity;
import com.example.trivia.mappers.entity.QuestionEntityMapper;
import com.example.trivia.mappers.model.GameMapper;
import com.example.trivia.mappers.model.QuestionMapper;
import com.example.trivia.model.AnswerQuestionResult;
import com.example.trivia.model.Game;
import com.example.trivia.model.Question;
import com.example.trivia.model.enums.GameCategory;
import com.example.trivia.model.enums.GameDifficulty;
import com.example.trivia.repository.AnswerRepository;
import com.example.trivia.repository.GameRepository;
import com.example.trivia.repository.QuestionRepository;
import com.example.trivia.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionEntityMapper questionEntityMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    GameMapper gameEntityMapper;

    @Autowired
    TriviaAPIClient triviaAPIClient;

    @Override
    public Game getGameById(Long gameId) {
        GameEntity game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));

        return gameEntityMapper.map(game);
    }

    private QuestionEntity fetchQuestion(Question question) {

        // 1. Check if question exists in our Question repository finding it by apiId
        QuestionEntity questionEntity = questionRepository.findByApiId(question.getApiId());
        // 2. If not found, create it with its answers
        if(questionEntity == null){
            questionEntity = questionRepository.save(questionEntityMapper.map(question));
            // save in repository
            for (AnswerEntity answer : questionEntity.getAnswers()) {
                answer.setQuestion(questionEntity);
                answerRepository.save(answer);
            }

        }

        return questionEntity;
    }

    @Override
    public Game createGame(Integer numberOfQuestions, GameDifficulty difficulty, List<GameCategory> categories) {
        GameEntity newGame = new GameEntity();

        List<QuestionEntity> questions =  triviaAPIClient
                .obtainQuestions(numberOfQuestions,categories,difficulty)
                .map(this::fetchQuestion)
                .collectList()
                .block();

        // Generate the associated questions
        List<GameQuestionEntity> gameQuestions = questions
                .stream()
                .map(question -> {
                    GameQuestionEntity gameQuestion = new GameQuestionEntity();
                    gameQuestion.setQuestion(question);
                    gameQuestion.setGame(newGame);
                    gameQuestion.setQuestionOrder(questions.indexOf(question));
                    List<AnswerEntity> answerEntity = gameQuestion.getQuestion().getAnswers();
                    for (AnswerEntity answer : answerEntity) {
                        answer.setQuestion(gameQuestion.getQuestion());
                    }

                    return gameQuestion;
                })
                .toList();

        newGame.setQuestions(gameQuestions);
        newGame.setDifficulty(difficulty);
        newGame.setCategories(categories);
        newGame.setFinished(Boolean.FALSE);
        gameRepository.save(newGame);
        return gameEntityMapper.map(newGame);
    }



    @Override
    public AnswerQuestionResult submitAnswer(Long gameId, Integer questionIndex, Long answerId) {

        // Get the data
        GameEntity game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        if(game.getFinished()){
            throw new RuntimeException("Game already finished");
        }

        if(questionIndex > game.getQuestions().size()) {
            throw new RuntimeException("Question index out of bounds");
        }

        GameQuestionEntity gameQuestion = game.getQuestions().get(questionIndex);
        List<AnswerEntity> answers = gameQuestion.getQuestion().getAnswers();
        AnswerEntity chosenAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        AnswerEntity correctAnswer = answers.stream()
                .filter(AnswerEntity::getCorrect)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No correct answer found"));


        // Validate question not already answered
        if (gameQuestion.getChosenAnswer() != null) {
            throw new RuntimeException("Question already answered");
        }

        // Store the answer
        gameQuestion.setChosenAnswer(chosenAnswer);
        gameRepository.save(game);

        // Return the result
        AnswerQuestionResult response = new AnswerQuestionResult();

        response.setIsCorrect(chosenAnswer.getCorrect());
        response.setCorrectAnswerId(correctAnswer.getId());
        response.setCorrectAnswerIndex(answers.indexOf(correctAnswer));

        return response;
    }

    @Override
    public Question getQuestionInfo(Long gameId, Integer questionIndex) {
        GameEntity game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        if(game.getFinished()){
            throw new RuntimeException("Game already finished");
        }
        if(questionIndex > game.getQuestions().size()) {
            throw new RuntimeException("Question index out of bounds");
        }

        GameQuestionEntity gameQuestion = game.getQuestions().get(questionIndex);

        return questionMapper.map(gameQuestion.getQuestion());
    }

    @Override
    public void finishFame(Long gameId) {
        GameEntity game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setFinished(true);
        gameRepository.save(game);
    }
}
