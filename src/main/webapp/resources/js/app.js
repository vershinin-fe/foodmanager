const React = require('react');
const ReactDOM = require('react-dom');
//const client = require('./client');

const App = () => (
    <div>
      <h1>Hello, World!</h1>
    </div>
);

ReactDOM.render(<App />,
    document.getElementById('react')
);