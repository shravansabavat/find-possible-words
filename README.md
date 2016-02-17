# find-possible words

> This class is to find the number of possible words in a grid of letters. Following assumptions are made for the solution.

 1. Words are formed in directions left to right, right to left, down to up, up to down,
    traverse up-left diagonal, up-right diagonal, down-left diagonal, down-right diagonal.
 
 2. A list of valid words are supplied through a text document. As of now list of valid words are stored in validWords.txt under the same level of this class.
 
 3. A list of block of words is supplied at runtime as a system input to this program to search for valid words from the valid-words.txt file. For example
    * TAMHAT(enter)
    * AIRQDR(enter)
    * MONCRT(enter)
    * EDRTCR(enter)
    * (enter) - And the last line should be an empty for the input to exit

#####How to run the code
```bash
> git clone https://github.com/shravansabavat/find-possible-words.git
```

* Import the cloned project into eclipse
* Open Solution.java in eclipse
* Right click and run as Java Application
* Open console, it would be waiting for an input from the user
* Give each line of letter in the console and enter
* When there is an input with no letter in it, the input stream would end and the program outputs the number of possible words that match the valid-words.txt words.
