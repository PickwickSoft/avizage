/** @type {import('tailwindcss').Config} */
const defaultTheme = require('tailwindcss/defaultTheme');
module.exports = {
  prefix: 'tw-',
  important: true,
  content: ['./src/**/*.{html,ts}'],
  theme: {
    extend: {
      colors: {
        primary: 'rgb(15 23 42)',
      },
    },
    screens: {
      xs: '400px',
      ...defaultTheme.screens,
    },
  },
  plugins: [],
};
