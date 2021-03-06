package br.com.caelum.tubaina.parser.html.desktop;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.tubaina.util.CommandExecutor;

public class HtmlSyntaxHighlighterTest {

    private String sampleCode;

    @Before
    public void setUp() {
        sampleCode = "public class Foo {\n" + "public int Bar(){\n" + "return 0;\n" + "}\n" + "}";
    }

    @Test
    public void shouldCallPygmentsWithJavaLexer() throws Exception {
        CommandExecutor executor = mock(CommandExecutor.class);
        String code = "public class Foo {\n" + "public int Bar(){\n" + "return 0;\n" + "}\n" + "}";
        HtmlSyntaxHighlighter highlighter = new HtmlSyntaxHighlighter(executor);
        highlighter.highlight(code, "java", false);
        String encoding = System.getProperty("file.encoding");
        List<String> arguments = Arrays.asList("pygmentize", "-O", "encoding=" + encoding
                + ",outencoding=UTF-8", "-f", "html", "-l", "java");
        verify(executor).execute(eq(arguments), eq(sampleCode));
    }

    @Test
    public void shouldCallPygmentsWithNumberedLinesOption() throws Exception {
        CommandExecutor executor = mock(CommandExecutor.class);
        HtmlSyntaxHighlighter highlighter = new HtmlSyntaxHighlighter(executor);
        highlighter.highlight(sampleCode, "java", true);
        String encoding = System.getProperty("file.encoding");
        List<String> arguments = Arrays.asList("pygmentize", "-O", "encoding=" + encoding
                + ",outencoding=UTF-8,linenos=inline", "-f", "html", "-l", "java");
        verify(executor).execute(eq(arguments), eq(sampleCode));
    }

    @Test
    public void shouldCallPygmentsWithHlLines() throws Exception {
        CommandExecutor executor = mock(CommandExecutor.class);
        HtmlSyntaxHighlighter highlighter = new HtmlSyntaxHighlighter(executor);

        List<Integer> lines = Arrays.asList(1, 2, 5);

        highlighter.highlight(sampleCode, "java", false, lines);

        String encoding = System.getProperty("file.encoding");

        List<String> arguments = Arrays.asList("pygmentize", "-O", "encoding=" + encoding
                + ",outencoding=UTF-8,hl_lines=1 2 5", "-f", "html", "-l", "java");

        verify(executor).execute(eq(arguments), eq(sampleCode));
    }

}
