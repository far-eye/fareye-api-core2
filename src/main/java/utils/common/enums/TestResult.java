package utils.common.enums;

public enum TestResult {
    PASSED(1),
    FAILED(2),
    SKIPPED(3);


    private final int code;

    TestResult(int code) {
        this.code = code;
    }

    public static String getStatus(int code) {
        for(TestResult result:TestResult.values()){
            if(code==result.code)
                return result.name();
        }
        return null;
    }
}
