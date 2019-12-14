package cricketleagueanalyser;

public class CricketLeagueAnalyserException extends Throwable {
        enum ExceptionType {
            CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE,NO_CENSUS_DATA,INCORRECT_HEADER, DELIMETER_ERROR, ERROR_WHILE_LOADING
        }
        ExceptionType type;
    public CricketLeagueAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
        }
    }
