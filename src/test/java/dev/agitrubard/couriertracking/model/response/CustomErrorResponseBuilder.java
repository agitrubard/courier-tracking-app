package dev.agitrubard.couriertracking.model.response;

public class CustomErrorResponseBuilder {

    public static final CustomErrorResponse VALIDATION_ERROR = CustomErrorResponse.builder()
            .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
            .build();

}
