package com.codestates.question.mapper;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.mapper.AnswerMapper;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.mapper.CommentMapper;
import com.codestates.question.dto.QuestionDto;
import com.codestates.question.dto.QuestionResponseDto;
import com.codestates.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel ="spring")
public interface QuestionMapper {
    AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);
    CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    @Mapping(source="m_id", target = "member.m_id")
    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);

    @Mapping(source="m_id", target = "member.m_id")
    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);

    @Mapping(source = "m_id", target = "member.m_id")
    Question questiondeleteDtoToQuestion(QuestionDto.Delete questionDeleteDto);

    default QuestionResponseDto questionToQuestionResponseDto(Question question){
        QuestionResponseDto questionResponseDto =
                QuestionResponseDto.builder()
                        .q_id(question.getQ_id())
                        .q_title(question.getQ_title())
                        .q_content(question.getQ_content())
                        .m_name(question.getMember().getName())
                        .m_id(question.getMember().getM_id())
                        .suggestedCount(question.getSuggestedCount())
                        .q_status(question.getQ_status())
                        .viewCount(question.getViewCount())
                        .answerCount(question.getAnswers().stream().count())
                        .createAt(question.getCreatedAt())
                        .modifiedAt(question.getModifiedAt())
                        //TODO List<Answer>, List<Comment> 스트림 처리 -> dto 처리 해야함
                        .answers(question.getAnswers().stream()
                                .filter(answer -> !answer.getA_status().equals(Answer.AnswerStatus.ANSWER_DELETE))
                                .map(answer -> answerMapper.answerToAnswerResponseDto(answer)).collect(Collectors.toList()))
                        .comments(question.getComments().stream()
                                .filter(comment -> !comment.getC_status().equals(Comment.CommentStatus.COMMENT_DELETE))
                                .map(comment -> commentMapper.CommentToCommentResponseDto(comment)).collect(Collectors.toList()))
                        .build();

        return questionResponseDto;
    }
    @Mapping(source = "m_id", target = "member.m_id")
    Question acceptAnswerPatchDtoToQuestion(QuestionDto.AcceptAnswerPatch acceptAnswerPatch);

    default List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions){
        List<QuestionResponseDto> responseDtos = questions.stream()
                .filter(question -> !question.getQ_status().equals(Question.QuestionStatus.QUESTION_DELETE))
                .map(question -> questionToQuestionResponseDto(question))
                .collect(Collectors.toList());
        return responseDtos;
    };
}
