package refactoring;

/*
 * This code has been extracted from OpenbravoERP:
 * https://code.openbravo.com/erp/stable/2.50-bp/file/cec9b1da72ed/src/org/openbravo/base/model/Entity.java
 */

class Log{
    public void warn(String msg){
        System.out.println(msg);
    }
}

public class Entity {
    private static final char[] ILLEGAL_ENTITY_NAME_CHARS = new char[]{'*', '?'};
    private String name;
    public Log log = new Log();

    public void setName(String name) {
        // repair the name if it contains any illegal character
        this.name = removeIllegalChars(name);
    }

    private String removeIllegalChars(String fromName) {
        final char[] chars = fromName.toCharArray();
        final StringBuilder newName = new StringBuilder();
        boolean nameChanged = false;
        for (char c : chars) {
            boolean hasIllegalChar = false;
            for (char illegalChar : ILLEGAL_ENTITY_NAME_CHARS) {
                if (c == illegalChar) {
                    hasIllegalChar = true;
                    break;
                }
            }
            if (hasIllegalChar) {
                nameChanged = true;
                continue;
            }
            newName.append(c);
        }
        if (nameChanged) {
            log.warn("The entity name " + fromName
                    + " contains illegal characters, it has been repaired to " + newName);
        } else {
            // check for other less normal characters
            for (char c : fromName.trim().toCharArray()) {
                final boolean normalChar = ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')
                        || ('a' <= c && c <= 'z') || c == '_';
                if (!normalChar) {
                    log.warn("The entity name " + fromName + " contains a character (" + c
                            + ") which could result in issues in HQL or "
                            + "webservices. Use characters from a to z, A to Z or 0 to 9 or the _");
                }
            }
        }
        return newName.toString();
    }


}