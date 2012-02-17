# What it's for

To finally get that piano song under your fingers!

# How it does it

Start with a piano MIDI file that defines one track for each hand.  This project will walk you through learning that song: repeating one hand, then the other, then both; starting slowly, getting faster; back-tracking so you can put the measures together.

It's that self-discipline you always wished you had!

# Technical specifics

Yeah, this is a command-line app.

You'll have to build the project into a jar yourself.  Then run it:

    java -jar <project-jar>.jar

The ensuing error message will give you a run-down of the parameters.

Once you're under way, use `return` to indicate that you've mastered a measure/section.  There's currently no ending condition -- expect a celebratory java explosion when you reach the end of the song!

# Requirements

Java (SE) 1.5 +

[JCommander](https://github.com/cbeust/jcommander) is rolled directly into the source.
