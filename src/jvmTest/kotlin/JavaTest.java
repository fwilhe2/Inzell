//import com.github.fwilhe.inzell.Column;
//import com.github.fwilhe.inzell.SpreadsheetBuilder;
//import com.github.fwilhe.inzell.StandardLibrary;
//import org.junit.Test;
//
//class JavaTest {
//    public static double foo(int i) {
//        return Math.sqrt(i);
//    }
//
//    @Test
//    public void foo() {
//        var s = new SpreadsheetBuilder()
//                .setCaption("test")
//                .addColumn(new Column("sqrt", JavaTest::foo))
//                .addColumn(new Column("cosine", StandardLibrary::cosine))
//                .build();
//    }
//}