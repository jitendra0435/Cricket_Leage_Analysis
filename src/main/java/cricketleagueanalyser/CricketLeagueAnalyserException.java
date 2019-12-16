package cricketleagueanalyser;

public class CricketLeagueAnalyserException extends Throwable {
        enum ExceptionType {
            CENSUS_FILE_PROBLEM,ERROR_WHILE_LOADING
        }
        ExceptionType type;
    public CricketLeagueAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
        }
    }
