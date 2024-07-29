/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        primary: "#DB5375",
        secondy: "#729EA1",
        secondyHover: "#577F82"
      }
    },
  },
  plugins: [],
}