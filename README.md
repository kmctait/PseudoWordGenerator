# PseudoWordGenerator

Program to generate pseudo EN words using a bigram model of the EN language

A wordlist of the n most frequent words in EN is also given as input to filter out any words generated that are already members of the EN language

## Running the package

The project is compiled and executed using standard Maven compiler plugin functions

Otherwise, a Jar package is already given, with the wordlist and bigram model packaged, and is run in the standard fashion:

```bash
java -jar RandomChars.jar
```

## TO DO

- generate/find bigram model for different languages
- order by most probable pseudo-words according to bigram model
- sort output by pseudo-word length
- remove duplicate CVC patterns


## References

[Norvig](http://norvig.com/mayzner.html)

[Cornell Uni](https://www.math.cornell.edu/~mec/2003-2004/cryptography/subs/digraphs.html)

[lydell on GitHub](https://gist.github.com/lydell/c439049abac2c9226e53)

