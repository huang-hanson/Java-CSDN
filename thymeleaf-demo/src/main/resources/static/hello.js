console.log('Hello from hello.js!');

function sayHello(name) {
    if (!name) {
        name = 'Unknown';
    }
    console.log(`Hello, ${name}!`);
}

// 添加这行代码，调用函数并传入 'Alice'
sayHello('Hanson');