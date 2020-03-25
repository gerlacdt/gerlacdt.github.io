// vortrag.js
// performance measuring
var time = function (fn) {
    var start = new Date().getTime();
    var args = Array.prototype.slice.call(arguments, 1);
    fn.apply(null, args);
    console.log(new Date().getTime() - start + "ms");
};

// 1, 1, 2, 3, 5, 8, 13, 21, 34,.....
var fib = function (n) {
    if (n <= 2) {
        return 1;
    } else {
        return fib(n-1) + fib(n-2);
    }
};

var memoize = function (fn) {
    var cache = {};

    return function () {
        var args = Array.prototype.slice.call(arguments);
        var args_string = JSON.stringify(args);
        if (cache[args_string] !== undefined) {
            return cache[args_string];
        } else {
            cache[args_string] = fn.apply(null, args);
            return cache[args_string];
        }
    };
};

//time uncacheed fibonacci
console.log(fib(6));
time(fib, 30);

fib = memoize(fib);
// time cached fibonacci
time(fib, 30);
// time(fib, 42);
