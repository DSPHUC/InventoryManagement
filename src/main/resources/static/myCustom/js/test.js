const numbers = [1, 2, 3, 4, 5];
const arrowReduce = numbers.reduce((accumulator, currentValue) => {
    console.log(accumulator);
    console.log(currentValue);

    return accumulator + currentValue;
});

const functionReduce = numbers.reduce(function (previousValue, currentValue) {
    console.log(previousValue);
    console.log(currentValue);

    return previousValue + currentValue;
});
console.log(arrowReduce);
console.log(functionReduce)