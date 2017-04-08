### Barsteward Darts

The name is actually bastard darts. But if this was ever released onto the market then barsteward darts is an easier name to consume

You can find the rules [here](http://www.angelfire.com/mac/qwik/Game_FAQ/bastard_darts_faqs.htm)

## Requirements

    - Android Studio
    - Kotlin Plugin for Intellij Idea/Android Studio

## Goals

1. Make a relatively complex game with the best OOP concepts ie...
    - Following the concepts from [Clean Code](http://www.goodreads.com/book/show/3735293-clean-code)
    - Unit tested completely

2. Write in Java and Kotlin simultaeulely so that...
    - We can understand the beauty of kotlin
    - Understand kotlin better
    - Compare the languages from a better perspective
    - Become better programmers

## Project Structure

There are 3 modules.

1. javadarts - The java representation of the app.
2. kotlindarts - The kotlin representation of the app.
3. resources - There is a problem in this project that one language module might progress might progress more than the other
this ensures that the UI cannot. It ensures that both languages modules are updated if the xml representation of the UI is changed.

## Contributions

***The problem*** 2 language modules might progress at different times.

***The solution*** feel free to write anything in any language (as long as it is unit tested). Then you have 3 options
    1. Use the kotlin plugin to convert to the opposite language and fix the problems
    2. Write in the opposite language as well as the original
    3. Leave a github issue for the next person to write the conversion

***Hard rule: You can't write anything extra in any language if there is an issue which needs a translation to the other language.***

***Soft rule*** Send a PR with your changes. This would be really nice. Especially if it touches what, will probably,
 become the "game engine" but it really depends on how many people are willing to look at PRs


## Future Ideas (these could be moved to Github issues)

 - Make a module that uses reactive streams spec (either module could work as Kotlin can interop with java)
 - Compile to Javascript(from kotlin) and make a web version
 - Introduce CI, travis might be a good call as it is free for open source
 - Compile to kotlin native, at the moment LLVM compilation is still aplha
 - Make and IOS when kotlin native supports IOS and has interop with swift/objective C