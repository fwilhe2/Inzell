(function (_, Kotlin) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var joinToString = Kotlin.kotlin.collections.joinToString_fmv235$;
  var repeat = Kotlin.kotlin.text.repeat_94bcnn$;
  var padEnd = Kotlin.kotlin.text.padEnd_vrc1nu$;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var elementAt = Kotlin.kotlin.collections.elementAt_ba2ldo$;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var numberToInt = Kotlin.numberToInt;
  var Random = Kotlin.kotlin.random.Random;
  var Math_0 = Math;
  CsvPrinter.prototype = Object.create(SpreadsheetPrinter.prototype);
  CsvPrinter.prototype.constructor = CsvPrinter;
  MarkdownPrinter.prototype = Object.create(SpreadsheetPrinter.prototype);
  MarkdownPrinter.prototype.constructor = MarkdownPrinter;
  HtmlPrinter.prototype = Object.create(SpreadsheetPrinter.prototype);
  HtmlPrinter.prototype.constructor = HtmlPrinter;
  function Column(title, function_0) {
    this.title = title;
    this.function_0 = function_0;
  }
  Column.prototype.eval_za3lpa$ = function (i) {
    return this.function_0(i);
  };
  Column.prototype.evalInt_za3lpa$ = function (i) {
    var tmp$;
    return typeof (tmp$ = this.function_0(i)) === 'number' ? tmp$ : throwCCE();
  };
  Column.prototype.evalDouble_za3lpa$ = function (i) {
    var tmp$;
    return typeof (tmp$ = this.function_0(i)) === 'number' ? tmp$ : throwCCE();
  };
  Column.prototype.evalString_za3lpa$ = function (i) {
    var tmp$;
    return typeof (tmp$ = this.function_0(i)) === 'string' ? tmp$ : throwCCE();
  };
  Column.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Column',
    interfaces: []
  };
  function Sheet(columns, caption) {
    if (caption === void 0)
      caption = '(No caption provided)';
    this.columns = columns;
    this.caption = caption;
  }
  Sheet.prototype.row_za3lpa$ = function (index) {
    var $receiver = this.columns;
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.eval_za3lpa$(index));
    }
    return destination;
  };
  Sheet.prototype.title_za3lpa$ = function (index) {
    return this.columns.get_za3lpa$(index).title;
  };
  Sheet.prototype.forEachFunction_3cmpgc$ = function (function_0) {
    var tmp$;
    tmp$ = this.columns.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      function_0(element);
    }
  };
  Sheet.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Sheet',
    interfaces: []
  };
  function SpreadsheetPrinter(sheet, numberOfRows) {
    if (numberOfRows === void 0)
      numberOfRows = 10;
    this.sheet = sheet;
    this.numberOfRows = numberOfRows;
  }
  SpreadsheetPrinter.prototype.printToStandardOut = function () {
    println(this.toString());
  };
  SpreadsheetPrinter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SpreadsheetPrinter',
    interfaces: []
  };
  function CsvPrinter(sheet) {
    SpreadsheetPrinter.call(this, sheet);
  }
  function CsvPrinter$toString$lambda(column) {
    return column.title;
  }
  CsvPrinter.prototype.toString = function () {
    var tmp$;
    var stringBuilder = StringBuilder_init();
    stringBuilder.append_pdl1vj$(joinToString(this.sheet.columns, ';', void 0, void 0, void 0, void 0, CsvPrinter$toString$lambda)).append_pdl1vj$('\n');
    tmp$ = this.numberOfRows;
    for (var row = 1; row <= tmp$; row++) {
      var $receiver = this.sheet.columns;
      var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(item.eval_za3lpa$(row));
      }
      stringBuilder.append_pdl1vj$(joinToString(destination, ';')).append_pdl1vj$('\n');
    }
    return stringBuilder.toString();
  };
  CsvPrinter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CsvPrinter',
    interfaces: [SpreadsheetPrinter]
  };
  function MarkdownPrinter(sheet) {
    SpreadsheetPrinter.call(this, sheet);
  }
  function MarkdownPrinter$toString$lambda(column) {
    return column.title;
  }
  function MarkdownPrinter$toString$lambda_0(column) {
    return repeat('-', column.title.length);
  }
  MarkdownPrinter.prototype.toString = function () {
    var tmp$;
    var stringBuilder = StringBuilder_init();
    stringBuilder.append_pdl1vj$(joinToString(this.sheet.columns, ' | ', void 0, void 0, void 0, void 0, MarkdownPrinter$toString$lambda)).append_pdl1vj$('\n');
    stringBuilder.append_pdl1vj$(joinToString(this.sheet.columns, ' | ', void 0, void 0, void 0, void 0, MarkdownPrinter$toString$lambda_0)).append_pdl1vj$('\n');
    tmp$ = this.numberOfRows;
    for (var row = 1; row <= tmp$; row++) {
      var $receiver = this.sheet.columns;
      var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(padEnd(item.eval_za3lpa$(row).toString(), item.title.length));
      }
      stringBuilder.append_pdl1vj$(joinToString(destination, ' | ')).append_pdl1vj$('\n');
    }
    return stringBuilder.toString();
  };
  MarkdownPrinter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MarkdownPrinter',
    interfaces: [SpreadsheetPrinter]
  };
  function HtmlPrinter(sheet) {
    SpreadsheetPrinter.call(this, sheet);
  }
  HtmlPrinter.prototype.toString = function () {
    var tmp$;
    var stringBuilder = StringBuilder_init();
    stringBuilder.append_pdl1vj$('<table>').append_s8itvh$(10);
    var value = '  <caption>' + this.sheet.caption + '<\/caption>';
    stringBuilder.append_pdl1vj$(value).append_s8itvh$(10);
    stringBuilder.append_pdl1vj$('  <tr>').append_s8itvh$(10);
    var times = this.sheet.columns.size;
    for (var index = 0; index < times; index++) {
      var value_0 = '    <th>' + this.sheet.columns.get_za3lpa$(index).title + '<\/th>';
      stringBuilder.append_pdl1vj$(value_0).append_s8itvh$(10);
    }
    var value_1 = '  <\/tr>';
    stringBuilder.append_pdl1vj$(value_1).append_s8itvh$(10);
    tmp$ = this.numberOfRows;
    for (var row = 1; row <= tmp$; row++) {
      stringBuilder.append_pdl1vj$('  <tr>').append_s8itvh$(10);
      var tmp$_0;
      tmp$_0 = this.sheet.columns.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        var value_2 = '    <td>' + element.eval_za3lpa$(row).toString() + '<\/td>';
        stringBuilder.append_pdl1vj$(value_2).append_s8itvh$(10);
      }
      var value_3 = '  <\/tr>';
      stringBuilder.append_pdl1vj$(value_3).append_s8itvh$(10);
    }
    var value_4 = '<\/table>';
    stringBuilder.append_pdl1vj$(value_4).append_s8itvh$(10);
    return stringBuilder.toString();
  };
  HtmlPrinter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HtmlPrinter',
    interfaces: [SpreadsheetPrinter]
  };
  function spreadsheet$ObjectLiteral(closure$theCaption, closure$columns) {
    this.closure$theCaption = closure$theCaption;
    this.closure$columns = closure$columns;
  }
  spreadsheet$ObjectLiteral.prototype.caption_61zpoe$ = function (caption) {
    this.closure$theCaption.v = caption;
  };
  spreadsheet$ObjectLiteral.prototype.column_cbshfx$ = function (title, function_0) {
    this.closure$columns.add_11rb$(new Column(title, function_0));
  };
  spreadsheet$ObjectLiteral.prototype.add_3af9ed$ = function (column) {
    this.closure$columns.add_11rb$(column);
  };
  spreadsheet$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Spreadsheet]
  };
  function spreadsheet(builder) {
    var columns = ArrayList_init_0();
    var theCaption = {v: '(No caption provided)'};
    builder(new spreadsheet$ObjectLiteral(theCaption, columns));
    return new Sheet(columns, theCaption.v);
  }
  function Spreadsheet() {
  }
  Spreadsheet.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Spreadsheet',
    interfaces: []
  };
  function buildFunctionOf$lambda(closure$values) {
    return function (i) {
      return closure$values[i];
    };
  }
  function buildFunctionOf(values) {
    return buildFunctionOf$lambda(values);
  }
  function buildFunctionOf$lambda_0(closure$values) {
    return function (i) {
      return elementAt(closure$values, i);
    };
  }
  function buildFunctionOf_0(values) {
    return buildFunctionOf$lambda_0(values);
  }
  function isEven(x) {
    return x % 2 === 0;
  }
  function isPrime(x) {
    var tmp$;
    if (x < 2) {
      return false;
    }tmp$ = numberToInt(Math_0.sqrt(x));
    for (var i = 2; i <= tmp$; i++) {
      if (x % i === 0) {
        return false;
      }}
    return true;
  }
  function count(x) {
    return x;
  }
  function sine(x) {
    return Math_0.sin(x);
  }
  function cosine(x) {
    return Math_0.cos(x);
  }
  function tangent(x) {
    return Math_0.tan(x);
  }
  function logarithm(x) {
    return Math_0.log(x);
  }
  function absolute(x) {
    return Math_0.abs(x);
  }
  function powerOfTwo(x) {
    return Math_0.pow(x, 2);
  }
  function random(x) {
    return Random.Default.nextDouble();
  }
  var package$com = _.com || (_.com = {});
  var package$github = package$com.github || (package$com.github = {});
  var package$fwilhe = package$github.fwilhe || (package$github.fwilhe = {});
  var package$inzell = package$fwilhe.inzell || (package$fwilhe.inzell = {});
  package$inzell.Column = Column;
  package$inzell.Sheet = Sheet;
  package$inzell.SpreadsheetPrinter = SpreadsheetPrinter;
  package$inzell.CsvPrinter = CsvPrinter;
  package$inzell.MarkdownPrinter = MarkdownPrinter;
  package$inzell.HtmlPrinter = HtmlPrinter;
  package$inzell.spreadsheet_7t5sly$ = spreadsheet;
  package$inzell.Spreadsheet = Spreadsheet;
  package$inzell.buildFunctionOf_9mqe4v$ = buildFunctionOf;
  package$inzell.buildFunctionOf_wlgces$ = buildFunctionOf_0;
  package$inzell.isEven_za3lpa$ = isEven;
  package$inzell.isPrime_za3lpa$ = isPrime;
  package$inzell.count_za3lpa$ = count;
  package$inzell.sine_za3lpa$ = sine;
  package$inzell.cosine_za3lpa$ = cosine;
  package$inzell.tangent_za3lpa$ = tangent;
  package$inzell.logarithm_za3lpa$ = logarithm;
  package$inzell.absolute_za3lpa$ = absolute;
  package$inzell.powerOfTwo_za3lpa$ = powerOfTwo;
  package$inzell.random_za3lpa$ = random;
  Kotlin.defineModule('inzell-jsLegacy', _);
  return _;
}(module.exports, require('kotlin')));

//# sourceMappingURL=inzell-jsLegacy.js.map
