/* GENERATED SOURCE. DO NOT MODIFY. */
// © 2022 and later: Unicode, Inc. and others.
// License & terms of use: http://www.unicode.org/copyright.html
package android.icu.text;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import android.icu.impl.personname.PersonNameFormatterImpl;

/**
 * A class for formatting names of people.  Takes raw name data for a person and renders it into a string according to
 * the caller's specifications, taking into account how people's names are rendered in the caller's locale.
 *
 * The Length, Usage, and Formality options can be used to get a wide variety of results.  In English, they would
 * produce results along these lines:
 * <table border="1">
 *     <tr>
 *         <th rowspan="2">
 *         </th>
 *         <th colspan="2">
 *             REFERRING
 *         </th>
 *         <th colspan="2">
 *             ADDRESSING
 *         </th>
 *         <th colspan="2">
 *             MONOGRAM
 *         </th>
 *     </tr>
 *     <tr>
 *         <th>FORMAL</th>
 *         <th>INFORMAL</th>
 *         <th>FORMAL</th>
 *         <th>INFORMAL</th>
 *         <th>FORMAL</th>
 *         <th>INFORMAL</th>
 *     </tr>
 *     <tr>
 *         <th>LONG</th>
 *         <td>James Earl Carter Jr.</td>
 *         <td>Jimmy Carter</td>
 *         <td>Mr. Carter</td>
 *         <td>Jimmy</td>
 *         <td>JEC</td>
 *         <td>JC</td>
 *     </tr>
 *     <tr>
 *         <th>MEDIUM</th>
 *         <td>James E. Carter Jr.</td>
 *         <td>Jimmy Carter</td>
 *         <td>Mr. Carter</td>
 *         <td>Jimmy</td>
 *         <td>C</td>
 *         <td>J</td>
 *     </tr>
 *     <tr>
 *         <th>SHORT</th>
 *         <td>J. E. Carter</td>
 *         <td>Jimmy Carter</td>
 *         <td>Mr. Carter</td>
 *         <td>Jimmy</td>
 *         <td>C</td>
 *         <td>J</td>
 *     </tr>
 * </table>
 *
 * @deprecated This API is for technology preview only.
 * @hide Only a subset of ICU is exposed in Android
 * @hide draft / provisional / internal are hidden on Android
 */
@Deprecated
public class PersonNameFormatter {
    //==============================================================================
    // Parameters that control formatting behavior

    /**
     * Specifies the desired length of the formatted name.
     * @deprecated This API is for technology preview only.
     * @hide Only a subset of ICU is exposed in Android
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public enum Length {
        /**
         * The longest name length.  Generally uses most of the fields in the name object.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        LONG,

        /**
         * The most typical name length.  Generally includes the given name and surname, but generally
         * not most of the other fields.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        MEDIUM,

        /**
         * A shortened name.  Skips most fields and may abbreviate some name fields to just their initials.
         * When Formality is INFORMAL, may only include one field.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        SHORT
    }

    /**
     * Specifies the intended usage of the formatted name.
     * @deprecated This API is for technology preview only.
     * @hide Only a subset of ICU is exposed in Android
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public enum Usage {
        /**
         * Used for when the name is going to be used to address the user directly: "Turn left here, John."
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        ADDRESSING,

        /**
         * Used in general cases, when the name is used to refer to somebody else.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        REFERRING,

        /**
         * Used to generate monograms, short 1 to 3-character versions of the name suitable for use in things
         * like chat avatars.  In English, this is usually the person's initials, but this isn't true in all
         * languages.  When the caller specifies Usage.MONOGRAM, the Length parameter can be used to get different
         * lengths of monograms: Length.SHORT is generally a single letter; Length.LONG may be as many as three or four.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        MONOGRAM
    }

    /**
     * Specifies the intended formality of the formatted name.
     * @deprecated This API is for technology preview only.
     * @hide Only a subset of ICU is exposed in Android
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public enum Formality {
        /**
         * The more formal version of the name.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        FORMAL,

        /**
         * The more informal version of the name.  In English, this might omit fields or use the "informal" variant
         * of the given name.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        INFORMAL
    }

    /**
     * Additional options to customize the behavior of the formatter.
     * @deprecated This API is for technology preview only.
     * @hide Only a subset of ICU is exposed in Android
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public enum Options {
        /**
         * Causes the formatter to generate results suitable for inclusion in a sorted list.  For GN-first languages,
         * this generally means moving the surname to the beginning of the string, with a comma between it and
         * the rest of the name: e.g., "Carter, James E. Jr.".
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        SORTING,

        /**
         * Requests that the surname in the formatted result be rendered in ALL CAPS.  This is often done with
         * Japanese names to highlight which name is the surname.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        SURNAME_ALLCAPS
    }

    private final PersonNameFormatterImpl impl;

    //==============================================================================
    // Builder for PersonNameFormatter

    /**
     * A utility class that can be used to construct a PersonNameFormatter.
     * Use PersonNameFormatter.builder() to get a new instance.
     * @deprecated This API is for technology preview only.
     * @hide Only a subset of ICU is exposed in Android
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public static class Builder {
        /**
         * Sets the locale for the formatter to be constructed.
         * @param locale The new formatter locale.  May not be null.
         * @return This builder.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        public Builder setLocale(Locale locale) {
            if (locale != null) {
                this.locale = locale;
            }
            return this;
        }

        /**
         * Sets the name length for the formatter to be constructed.
         * @param length The new name length.
         * @return This builder.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        public Builder setLength(Length length) {
            this.length = length;
            return this;
        }

        /**
         * Sets the name usage for the formatter to be constructed.
         * @param usage The new name length.
         * @return This builder.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        public Builder setUsage(Usage usage) {
            this.usage = usage;
            return this;
        }

        /**
         * Sets the name formality for the formatter to be constructed.
         * @param formality The new name length.
         * @return This builder.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        public Builder setFormality(Formality formality) {
            this.formality = formality;
            return this;
        }

        /**
         * Sets the options set for the formatter to be constructed.  The Set passed in
         * here replaces the entire options set the builder already has (if one has
         * already been set); this method doesn't modify the builder's options set.
         * @param options The new options set.
         * @return This builder.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        public Builder setOptions(Set<Options> options) {
            this.options = options;
            return this;
        }

        /**
         * Returns a new PersonNameFormatter with the values that were passed to this builder.
         * This method doesn't freeze or delete the builder; you can call build() more than once
         * (presumably after calling the other methods to change the parameter) to create more
         * than one PersonNameFormatter; you don't need a new Builder for each PersonNameFormatter.
         * @return A new PersonNameFormatter.
         * @deprecated This API is for technology preview only.
         * @hide draft / provisional / internal are hidden on Android
         */
        @Deprecated
        public PersonNameFormatter build() {
            return new PersonNameFormatter(locale, length, usage, formality, options);
        }

        private Builder() {
       }

        private Locale locale = Locale.getDefault();
        private Length length = Length.MEDIUM;
        private Usage usage = Usage.REFERRING;
        private Formality formality = Formality.FORMAL;
        private Set<Options> options = new HashSet<>();
    }

    //==============================================================================
    // Public API on PersonNameFormatter

    /**
     * Returns a Builder object that can be used to construct a new PersonNameFormatter.
     * @return A new Builder.
     * @deprecated This API is for technology preview only.
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a Builder object whose fields match those used to construct this formatter,
     * allowing a new formatter to be created based on this one.
     * @return A new Builder that can be used to create a new formatter based on this formatter.
     * @deprecated This API is for technology preview only.
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public Builder toBuilder() {
        Builder builder = builder();
        builder.setLocale(impl.getLocale());
        builder.setLength(impl.getLength());
        builder.setUsage(impl.getUsage());
        builder.setFormality(impl.getFormality());
        builder.setOptions(impl.getOptions());
        return builder;
    }

    /**
     * Formats a name.
     * @param name A PersonName object that supplies individual field values (optionally, with modifiers applied)
     *             to the formatter for formatting.
     * @return The name, formatted according to the locale and other parameters passed to the formatter's constructor.
     * @deprecated This API is for technology preview only.
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public String formatToString(PersonName name) {
        // TODO: Add a format() method that returns a FormattedPersonName object that descends from FormattedValue.
        return impl.formatToString(name);
    }

    //==============================================================================
    // Internal implementation
    private PersonNameFormatter(Locale locale, Length length, Usage usage, Formality formality, Set<Options> options) {
        this.impl = new PersonNameFormatterImpl(locale, length, usage, formality, options);
    }

    /**
     * @deprecated This API is for unit testing only.
     * @hide draft / provisional / internal are hidden on Android
     */
    @Deprecated
    public PersonNameFormatter(Locale locale, String[] patterns) {
        this.impl = new PersonNameFormatterImpl(locale, patterns);
    }
}
