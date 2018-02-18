package su.vfe.foodmanager.util.exception;

import org.springframework.http.HttpStatus;

public class FamilyMembershipChangeException extends ApplicationException {
    public static final String EXCEPTION_MEMBERSHIP_CHANGE_EXCEPTION = "exception.family.membershipChange";

    public FamilyMembershipChangeException(String userId) {
        super(ErrorType.DATA_ERROR, EXCEPTION_MEMBERSHIP_CHANGE_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, userId);
    }
}
