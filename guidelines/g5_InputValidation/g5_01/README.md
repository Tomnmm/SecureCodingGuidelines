# INPUT-1: Validate inputs
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Input from untrusted sources must be validated before use. Maliciously crafted inputs may cause problems, whether coming through method arguments or external streams. Examples include overflow of integer values and directory traversal attacks by including "../" sequences in filenames. Ease-of-use features should be separated from programmatic interfaces. Note that input validation must occur after any defensive copying of that input (see Guideline 6-2).

# INPUT-1: Integer overflow

![Author](https://img.shields.io/badge/Author-EmmanouilPerselis-blue.svg)
![Date](https://img.shields.io/badge/Date-20171208-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

## Simple Example
In the source code given , it is possible to give certain integer values as input (not bigger than 2.147.483.647 but big enough to surpas this value after multiplication with the second integer) so that the result value will be negative after it is overflowed.
This kind of vulnerabillity can be mitigated by using 

    BigInteger bi = BigInteger.valueOf(number1);
    BigInteger bi2 =BigInteger.valueOf(number2);

in combination with 

    try (Scanner sc = new Scanner(System.in)) {
            System.out.println("please enter first integer");
            String string = sc.nextLine();              //give 1231231233 as input
            number1 = Integer.parseInt(string);
        }catch(NumberFormatException ex){
            System.out.println("Input is not an integer. Exiting...");
            System.exit(1);
        } 
        
To check whether the input is indeed an integer. 

![Author](https://img.shields.io/badge/Author-BjarneRasera-blue.svg)
![Date](https://img.shields.io/badge/Date-20180123-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

An other solution could be to trap the integer overflow which was mentioned in g1-03. Make use of addExact, multiplyExact, ...

    try {
        System.out.println(Math.multiplyExact(number1, number2));
    }catch (ArithmeticException e){
            System.out.println("With multiplyExact: " + e);
    }