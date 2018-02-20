package br.com.welson.springboot.error;

public class ErrorDetails {
    protected String title;
    protected int status;
    protected String detail;
    protected long timestamp;
    protected String developerMessage;



    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public static final class Builder {
        protected String title;
        protected int status;
        protected String detail;
        protected long timestamp;
        protected String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.title = this.title;
            errorDetails.detail = this.detail;
            errorDetails.developerMessage = this.developerMessage;
            errorDetails.status = this.status;
            errorDetails.timestamp = this.timestamp;
            return errorDetails;
        }
    }
}
