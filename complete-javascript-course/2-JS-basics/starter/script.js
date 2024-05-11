// var firstName = 'John';
// console.log(firstName);

// var lastName = 'Smith';
// var age = 28;

// var fullAge = true;
// console.log(fullAge);

// var job;
// console.log(job);

// job = 'Teacher';
// console.log(job);

// var firstName = 'John';
// var age = 28;

// console.log(firstName + ' ' + age);

// var job, isMarried;
// job = 'teacher';
// isMarried = false;

// console.log(
//     firstName + ' is a ' +
//     age + ' year old ' +
//     job + '. Is he marrired? ' +
//     isMarried);

// // Variable mutation
// age = 'twenty eight';
// job = 'driver';

// //alert(
// // firstName + ' is a ' +
// // age + ' year old ' +
// // job + '. Is he marrired? ' +
// // isMarried);

// //var lastName = prompt('What is his last name?');
// //console.log(firstName + ' ' + lastName);

// /*
//  * Basic operators
//  */

// var now, yearJohn, yearMark;
// now = 2020;
// ageJohn = 28;
// ageMark = 33;

// // Math operators
// yearJohn = now - ageJohn;
// yearMark = now - ageMark;

// console.log(yearJohn);

// console.log(now + 2);
// console.log(now * 2);
// console.log(now / 10);

// // Logical operators
// var johnOlder = ageJohn < ageMark;
// console.log(johnOlder);

// // typeof operators
// console.log(typeof johnOlder);
// console.log(typeof ageJohn);
// console.log(typeof 'Mark is older than John');
// var x;
// console.log(typeof x);

/*
 Operator precedence
*/

// var now = 2020;
// var yearJohn = 1989;
// var fullAge = 18;

// var isFullAge = now - yearJohn >= fullAge; // true
// console.log(isFullAge);

// var ageJohn = now - yearJohn;
// var ageMark = 35;
// var agv = (ageJohn + ageMark) / 2;
// console.log(agv);

// // Multiple assignments
// var x, y;
// x = y = (3 + 5) * 4 - 6;
// console.log(x, y);

// // more operators
// x *= 2;
// console.log(x);
// x += 10;
// console.log(x);
// x--;
// console.log(x);

/***************************************
 * If else statements 
 */

// var firstName = 'John';
// var civilStatus = 'single';

// if (civilStatus === 'married') {
//     console.log(firstName + ' is married!');
// } else {
//     console.log(firstName + ' will hopefully marry soon :))');
// }

// var isMarried = true;
// if (isMarried) {
//     console.log(firstName + ' is married!');
// } else {
//     console.log(firstName + ' will hopefully marry soon :))');
// }

// var massMark = 78;
// var heightMark = 1.69;

// var massJohn = 92;
// var heightJohn = 1.95;

// var bmiMark = massMark / (heightMark * heightMark);
// var bmiJohn = massJohn / (heightJohn * heightJohn);

// if (bmiMark > bmiJohn) {
//     console.log('Mark\'s BMI is higher than John\'s');
// } else {
//     console.log('John\'s BMI is higher than Mark\'s');
// }



/******************
 * Boolean logic
 */

// var firstName = 'John';
// var age = 20;

// if (age < 13) {
//     console.log(firstName + ' is a boy.');
// } else if (age >= 13 && age < 20) {
//     console.log(firstName + ' is a teenager.');
// } else if (age >= 20 && age < 30) {
//     console.log(firstName + ' is a young man.');
// } else {
//     console.log(firstName + ' is a man.');
// }

/*********************
 * The ternary operator and switch statements
 */

// var firstName = 'John';
// var age = 22;

// age >= 18 ? console.log(firstName + ' drink beer.') :
//     console.log(firstName + ' drink juice.');

// var drink = age >= 18 ? 'beer' : 'juice';
// console.log(firstName + ' drink ' + drink);

// var job = 'instructor';

// switch (job) {
//     case 'teacher':
//     case 'instructor':
//         console.log(firstName + ' teaches kids how to code.');
//         break;
//     case 'driver':
//         console.log(firstName + ' drives an uber in Lisbon.');
//         break;
//     case 'designer':
//         console.log(firstName + ' desigsn beutiful websites.');
//         break;
//     default:
//         console.log(firstName + ' does something else.');
// }

// age = 52
// switch (true) {
//     case age < 13:
//         console.log(firstName + ' is a boy.');
//         break;
//     case age >= 13 && age < 20:
//         console.log(firstName + ' is a teenager.');
//         break;
//     case age >= 20 && age < 30:
//         console.log(firstName + ' is a young man.');
//         break;
//     default:
//         console.log(firstName + ' is a man.');
// }

/******************
 * Truthy and Falsy values and equality operators
 */

// Falsy values: undefined, null, 0, '', NaN
// Truthy values: Not falsy values

// var height;

// height = 23;

// if (height || height === 0) {
//     console.log('Variable is defined');
// } else {
//     console.log('Variable has not been defined');
// }

// if (height == '23') {
//     console.log('The == operator does type coercion!');
// }

// var avgJohn = (89 + 120 + 103) / 3;
// var avgMike = (116 + 94 + 123) / 3;
// var avgMary = (97 + 134 + 105) / 3;

// if (avgJohn > avgMike && avgJohn > avgMary) {
//     console.log('John\'s team wins with ' + avgJohn + ' points');
// } else if (avgMike > avgJohn && avgMike > avgMary) {
//     console.log('Mike\'s team wins with ' + avgMike + ' points');
// } else if (avgMary > avgJohn && avgMary > avgMike) {
//     console.log('Mary\'s team wins with ' + avgMary + ' points');
// } else {
//     console.log('There is a draw');
// }

/******************
 * Functions
 */

// function calculateAge(birthYear) {
//     return 2020 - birthYear;
// }

// var age = calculateAge(1990);
// console.log(age);

// function yearUntilRetirement(birthYear, firstName) {
//     var age = calculateAge(birthYear);
//     var retirement = 65 - age;
//     if (retirement > 0) {
//         console.log(firstName + ' retired in ' + retirement + ' years.');
//     } else {
//         console.log(firstName + ' is already retired.');
//     }
// }

// yearUntilRetirement(1990, 'John');
// yearUntilRetirement(1944, 'Mike');


/***********************
 * Function statements and expressions
 */

// Func expressions
// var whatDoYouDo = function(job, firstName) {
//     switch(job) {
//         case 'teacher':
//             return firstName + ' teaches kid how to code.';
//         case 'driver':
//             return firstName + ' drives a cab in Lisbon.';
//         case 'designer':
//             return firstName + ' designs beutiful websites.';
//         default:
//             firstName + ' does something else';            
//     }
// }

// console.log(whatDoYouDo('teacher', 'John'));


/********************
 * Arrays
 */

// var names = ['John', 'Mike', 'Mark'];
// var years = new Array(1990, 1969, 1948);

// console.log(names);
// console.log(names.length);

// names[1] = 'Ben';
// names[names.length] = 'Mary';
// console.log(names);

// // different data types

// var john = ['John', 'Smith', 1990, 'designer', false];

// john.push('blue');
// john.unshift('Mr. ');
// console.log(john);

// john.pop();
// john.pop();
// john.shift();
// console.log(john);

// console.log(john.indexOf(23));

// var isDesigner = john.indexOf('designer') === -1 ?
//     'John is not a designer' :
//     'John is a desinger';
// console.log(isDesigner);

/*********************
 * coding chanllenge 3
 */

// var tipArr, amountArr;
// var bills = [124, 48, 268];

// function tipCalculator(bill) {
//     var percentage;

//     if (bill < 50)
//         percentage = 0.2;
//     else if (bill >= 50 && bill < 200)
//         percentage = 0.15;
//     else
//         percentage = 0.1;

//     return percentage * bill;
// }

// tipArr = [tipCalculator(bills[0]),
//     tipCalculator(bills[1]),
//     tipCalculator(bills[2])
// ];
// console.log(tipArr);

// amountArr = [tipArr[0] + bills[0],
//     tipArr[1] + bills[1],
//     tipArr[2] + bills[2]
// ];

// console.log(amountArr);


/*******************
 * Objects and properties
 */

// var john = {
//     firstName: 'John',
//     lastName: 'Smith',
//     birthYear: 1990,
//     family: ['Jane', 'Mark', 'Bob', 'Emily'],
//     job: 'teacher',
//     isMarried: false
// };

// john.job = 'driver';
// john['isMarried'] = true;
// console.log(john);

// var jane = new Object();
// jane.firstName = 'Jane';
// jane.birthYear = 1989;
// jane['lastName'] = 'Smith';
// console.log(jane);

/************************
 * Objects and methods
 */

// var john = {
//     firstName: 'John',
//     lastName: 'Smith',
//     birthYear: 1990,
//     family: ['Jane', 'Mark', 'Bob', 'Emily'],
//     job: 'teacher',
//     isMarried: false,
//     calculateAge: function () {
//         this.age =  2020 - this.birthYear;
//     }
// };

// john.calculateAge();
// console.log(john);

/************************
 * Coding challenge 4
 */

// var john = {
//     fullName: 'John Smith',
//     mass: 92,
//     height: 1.95,
//     calBMI: function () {
//         this.bmi = this.mass / (this.height * this.height);
//         return this.bmi;
//     }
// };

// var mark = {
//     fullName: 'Mark Robson',
//     mass: 78,
//     height: 1.69,
//     calBMI: function () {
//         this.bmi = this.mass / (this.height * this.height);
//         return this.bmi;
//     }
// };

// john.calBMI();
// mark.calBMI();
// console.log(john, mark);

// if (john.calBMI() > mark.calBMI()) {
//     console.log('John\'s has a higher BMI than Mark\'s');
// } else if (john.calBMI() < mark.calBMI()) {
//     console.log('Mark\'s has a higher BMI than John\'s');
// } else {
//     console.log('they have the same BMI');
// }

/*******************************
 * Loops and iteration
 */

// for (var i = 0; i < 10; i++) {
//     console.log(i);
// }

// var john = ['John', 'Smith', 1990, 'designer', false, 'blue'];

// for (var i = 0; i < john.length; i++) {
//     console.log(john[i]);
// }

// var i = 0;
// while (i < john.length) {
//     console.log(john[i]);
//     i++;
// }

// continue and break statements
// var john = ['John', 'Smith', 1990, 'designer', false, 'blue'];

// for (var i = 0; i < john.length; i++) {
//     if (typeof (john[i]) !== 'string') continue;
//     console.log(john[i]);
// }

// for (var i = 0; i < john.length; i++) {
//     if (typeof (john[i]) !== 'string') break;
//     console.log(john[i]);
// }

// for (var i = john.length - 1; i >= 0; i--) {
//     console.log(john[i]);
// }

/***********************
 * Coding challenge 5
 */

// var john = {
//     fullName: 'John Smith',
//     bills: [124, 48, 268, 180, 42],
//     calcTips: function () {
//         this.tips = [];
//         this.finalValue = [];
//         for (var i = 0; i < this.bills.length; i++) {
//             var percentage;
//             var bill = this.bills[i];
//             if (bill < 50)
//                 percentage = 0.2;
//             else if (bill >= 50 && bill < 200)
//                 percentage = 0.15;
//             else
//                 percentage = 0.1;

//             this.tips[i] = bill * percentage;
//             this.finalValue[i] = bill + bill * percentage;
//         }
//     }
// };

// var mark = {
//     fullName: 'Mark Robson',
//     bills: [77, 375, 110, 45],
//     calcTips: function () {
//         this.tips = [];
//         this.finalValue = [];
//         for (var i = 0; i < this.bills.length; i++) {
//             var percentage;
//             var bill = this.bills[i];
//             if (bill < 100)
//                 percentage = 0.2;
//             else if (bill >= 100 && bill < 300)
//                 percentage = 0.1;
//             else
//                 percentage = 0.25;

//             this.tips[i] = bill * percentage;
//             this.finalValue[i] = bill + bill * percentage;
//         }
//     }
// };

// function calcAvg(tips) {
//     var sum = 0;
//     for (var i = 0; i < tips.length; i++) {
//         sum = sum + tips[i];
//     }
//     return sum / tips.length;
// }


// john.calcTips();
// console.log(john);

// mark.calcTips();
// console.log(mark);

// john.average = calcAvg(john.tips);
// mark.average = calcAvg(mark.tips);

// if (john.average > mark.average)
//     console.log(john.fullName + '\'s family pays higher tips, with an average of $' + john.average);
// else console.log(mark.fullName + '\'s family pays higher tips, with an average of $' + mark.average);



