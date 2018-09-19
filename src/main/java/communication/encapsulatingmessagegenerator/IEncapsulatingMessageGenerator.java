package communication.encapsulatingmessagegenerator;

import communication.messages.EncapsulatingMessage;

public interface IEncapsulatingMessageGenerator {
    EncapsulatingMessage generateMessage(Object content);
    String generateMessageString(Object object);
}
