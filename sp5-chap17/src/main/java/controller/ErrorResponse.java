package controller;

// RestController에서 응답이 에러 발생 상황일 때, json으로 리턴해 줄 객체
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
