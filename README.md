iampurpleenough
===============

I was looking for an excuse to try out the JSR 353 RI, and a former employee of mine ([Purple Scout](http://purplescout.se)) announced a competition mainly aimed towards students and junior developers. For me it was just the excuse I was looking for. While I didn't participate in the competition, I figured I might as well put my hack up.

A few decisions were made:
* No test-cases
  * It's made to be run exectly once under controlled circumstances. It only needs to work for exactly this case and adding tests wouldn't increase the value of the solution while adding cost in terms of development time.
* No javadocs
  * Not worth the effort since it's not a public API and the full code is small enough to fit on a page.
* What, putting floating point values into a hashmap!? o_0
  * In this particular case it happens to work. If the translation table changes, it is likely to need to change.
  * Really, don't do this.
  * If it hadn't worked I would probably have used a sorted list and looked through it for the closest match. If the list would have been larger or the number of "codes" larger, i would probably done a binary search.
