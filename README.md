Added a toolbar menu to access a payment section that works with donations. <br>
<img src="/screenshots/sumup1.jpg" height="400px"/> <img src="/screenshots/sumup2.jpg
" height="400px"/><img src="/screenshots/sumup3.jpg
" height="400px"/>
<br>

Pressing any of the buttons will take you to a SumUp Payment screen and a receipt option will be available. <br>
<img src="/screenshots/sumup4.jpg" height="400px"/> <img src="/screenshots/sumup6.jpg
" height="400px"/><img src="/screenshots/sumup7.jpg
" height="400px"/>

# Coder Notes
This was a equally difficult and fun test. 

For the donation screen I replicated the Activity-Fragment configurations. Both classes are under the donation package.
The Payment was handled in the SumUpPaymentActivity class under the SumUp package.
The SumUpAPI and the data structures under the dataModel are the Retrofit Classes.

Originally I wanted to have every to-do editable for 1€ or have the alarm functionality behind a paywall on top of the donation section. Due to time constraints and code complexity these changes will have to wait until the next revision.

# Bugs

On some configurations, the main screen won't dynamically adapt to new items being added. If the app is restarted, they will show correctly. 

Other issues might arise when connectivity is not optimal.

# Important

Android Plugin Version must remain 2.3.0. 
Updating Gradle might incur in unknown errors.


# Minimal ![Build Status](https://travis-ci.org/avjinder/Minimal-Todo.svg?branch=master)

<img src="/screenshots/app_icon.png" height="200px"/> <br>
A fully Material ToDo app with minimal features, just enough to be useful.


# Screenshots:
<img src="/screenshots/main_empty_light.png" height="400px"/> <img src="/screenshots/main_empty_dark.png" height="400px"/>
<img src="/screenshots/main_full_light.png" height="400px"/><img src="/screenshots/main_full_dark.png" height="400px"/>
<img src="/screenshots/add_todo_light.png" height="400px"/>
<img src="/screenshots/add_todo_dark.png" height="400px"/>
<img src="screenshots/screenshot_reminder_date.png" height="400px"/>
<img src="screenshots/screenshot_reminder_time.png" height="400px"/>
<img src="screenshots/todo_date_dark.png" height="400px"/>
<img src="screenshots/todo_time_dark.png" height="400px"/>
<img src="https://github.com/avjinder/Toodle/blob/master/screenshots/screenshot_notification.png" height="400px"/>
<img src="https://github.com/avjinder/Toodle/blob/master/screenshots/screenshot_todo_snooze.png" height="400px"/>

## Download
<a href="https://play.google.com/store/apps/details?id=com.avjindersinghsekhon.minimaltodo&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-AC-global-none-all-co-pr-py-PartBadges-Oct1515-1">
<img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/apps/en-play-badge.png" height="50px"/></a>




## App Icon
Designed by [Christopher Gundersen](cgundersen2020@gmail.com)
## Translations
* Spanish by [nanopc](https://github.com/nanopc) <br>
* German by [kaiwinter](https://github.com/kaiwinter) <br>
* Polish by [piotrek1543](https://github.com/piotrek1543) <br>
* Finnish by [Miikka Andersson](https://github.com/miikande) <br>
* French by [Nicola Spanti](http://www.nicola-spanti.info/)
* Bulgarian by [Georgy Hristov](http://www.georgyhristov.xyz/)

## Contributing
Looking to contribute something to Minimal? [Here's how you can help](/Contributing.md).


# License:
The MIT License (MIT)

Copyright (c) 2015 Avjinder

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

