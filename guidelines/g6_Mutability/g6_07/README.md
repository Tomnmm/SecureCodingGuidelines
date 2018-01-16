# MUTABLE-7: Treat output from untrusted object as input
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


The above guidelines on input objects apply when returned from untrusted objects. Appropriate copying and validation should be applied.

        private final Date start;
        private Date end;

        public void endWith(Event event) throws IOException {
            Date end = new Date(event.getDate().getTime());
            if (end.before(start)) {
                throw new IllegalArgumentException("...");
            }
            this.end = end;
        }

## ExampleG6G07
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180115-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-PENDING-orange.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Example code: Validate end date input is not before start date.