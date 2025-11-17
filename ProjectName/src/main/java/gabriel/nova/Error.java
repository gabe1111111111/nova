package gabriel.nova;
/**
 * errors will be handles by return values instead of thrown errors
 * this is an enum of all errors in the project
 * @author Gabriel Lacey
 */
public enum Error {
    __NO_ERROR__,
    __FILE_NOT_FOUND_EXCEPTION__,
    __FILE_TOO_LONG_EXCEPTION__,
    __UNEXPECTED_ERROR_WHILE_LOADING_FILE_,
    __SYNTAX_ERROR_UNCLOSED_CONTAINER_EXCEPTION__,
    __SYNTAX_ERROR_INCOMPLETE_PARAMETER_EXCEPTION__,
    __SYNTAX_ERROR_TOO_MANY_TOKENS__,
    __SYNTAX_ERROR_INCOMPLETE_COMMENT__
}