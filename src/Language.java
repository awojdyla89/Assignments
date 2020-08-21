import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a finite language.
 *
 * @author Dr. Jody Paul
 * @author ADAM_WOJDYLA
 * @version 1.3.1
 */
public final class Language implements Iterable<String>, java.io.Serializable {
    /**
     * The empty string.
     */
    private static final String EMPTY_STRING = "";
    /**
     * The empty set.
     */
    private static final Set<String> EMPTY_SET = new TreeSet<String>();

    /**
     * The set of strings in this language, initially empty.
     */
    private Set<String> strings;

    /**
     * Create a language with no strings.
     */
    public Language() {
        strings = new TreeSet<>();

    }

    /**
     * Indicates if this language has no strings.
     *
     * @return true if the language is equivalent to the empty set;
     * false otherwise
     */
    public boolean isEmpty() {


        return strings.size() == EMPTY_SET.size();
    }

    /**
     * Accesses the number of strings (cardinality) in this language.
     *
     * @return the cardinality of the language
     */
    public int cardinality() {

        return strings.size();
    }

    /**
     * Determines if a specified string is in this language.
     *
     * @param candidate the string to check
     * @return true if the string is in the language,
     * false if not in the language or the parameter is null
     */
    public boolean includes(final String candidate) {

        return candidate == null || strings.contains(candidate);
    }

    /**
     * Ensures that this language includes the given string
     * (adds it if necessary).
     *
     * @param memberString the string to be included in the language
     * @return true if this language changed as a result of the call
     */
    public boolean addString(final String memberString) {

        return strings.add(memberString);
    }

    /**
     * Ensures that this language includes all of the strings
     * in the given parameter (adds any as necessary).
     *
     * @param memberStrings the strings to be included in the language
     * @return true if this language changed as a result of the call
     */
    public boolean addAllStrings(final Collection<String> memberStrings) {

        Boolean boole = false;
        for (String str1 : strings) {
            boole = addString(str1) || boole;
        }
        return boole;
    }

    /**
     * Provides an iterator over the strings in this language.
     *
     * @return an iterator over the strings in this language in ascending order
     */
    public Iterator<String> iterator() {


        return strings.iterator();
    }

    /**
     * Creates a language that is the concatenation of this language
     * with another language.
     *
     * @param language the language to be concatenated to this language
     * @return the concatenation of this language with the parameter language
     */
    public Language concatenate(final Language language) {

        Language newLanguage = new Language();
        for (String str1 : this) {
            for (String str2 : language) {
                newLanguage.addString(str1.concat(str2));
            }
        }
        return newLanguage;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Language) {

            Boolean booleIsEqual = true;
            Language language = (Language) obj;
            if (language.cardinality() != this.cardinality()) {
                return false;
            }

            Iterator<String> itrOne = this.iterator();
            Iterator<String> itrTwo = language.iterator();

            while (itrOne.hasNext()) {
                booleIsEqual = itrOne.next().equals(itrTwo.next());
            }
            return booleIsEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        //TODO
        return Integer.MIN_VALUE;
    }
}