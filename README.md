iampurpleenough
===============

A few decisions were made:
* No test-cases
  * It's made to be run exectly once under controlled circumstances. It only needs to work for exactly this case and adding tests wouldn't increase the value of the solution while adding cost in terms of development time.
* No javadocs
  * Not worth the effort since it's not a public API and the full code is small enough to fit on a page.
* What, putting floating point values into a hashmap!? o_0
  * In this particular case it happens to work. If the translation table changes, it is likely to need to change.
  * Really, don't do this.
