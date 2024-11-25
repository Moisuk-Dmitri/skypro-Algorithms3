package exception;

public class EmptyParameterGivenException extends RuntimeException {

    public EmptyParameterGivenException(String e) {
        System.out.println(e);
    }
}
