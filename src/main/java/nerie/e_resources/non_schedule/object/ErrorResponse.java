package nerie.e_resources.non_schedule.object;

public class ErrorResponse {

	private ErrorDetails error;

	public ErrorResponse() {

	}

	public ErrorResponse(ErrorDetails erDetail) {
		this.error = erDetail;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorResponse [error=" + error + "]";
	}

}
