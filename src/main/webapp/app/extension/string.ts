String.prototype.capitalize = function (this: string) {
  return this.toLowerCase().replace(/(^\w)|(\s+\w)/g, letter => letter.toUpperCase());
};
