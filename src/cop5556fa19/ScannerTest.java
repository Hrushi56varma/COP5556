/* *
 * Developed  for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2019.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2019 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites or repositories,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2019
 */

package cop5556fa19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import javax.print.DocFlavor.STRING;

import static cop5556fa19.Token.Kind.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cop5556fa19.Scanner;
import cop5556fa19.Token;
import cop5556fa19.Scanner.LexicalException;

class ScannerTest {
	
	//I like this to make it easy to print objects and turn this output on and off
	static boolean doPrint = true;
	private void show(Object input) {
		if (doPrint) {
			System.out.println(input.toString());
		}
	}
	
	

	 /**
	  * Example showing how to get input from a Java string literal.
	  * 
	  * In this case, the string is empty.  The only Token that should be returned is an EOF Token.  
	  * 
	  * This test case passes with the provided skeleton, and should also pass in your final implementation.
	  * Note that calling getNext again after having reached the end of the input should just return another EOF Token.
	  * 
	  */
	@Test 
	void test0() throws Exception {
		Reader r = new StringReader("");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext()); 
		assertEquals(EOF, t.kind);
		show(t= s.getNext());
		assertEquals(EOF, t.kind);
	}

	/**
	 * Example showing how to create a test case to ensure that an exception is thrown when illegal input is given.
	 * 
	 * This "@" character is illegal in the final scanner (except as part of a String literal or comment). So this
	 * test should remain valid in your complete Scanner.
	 */
	@Test
	void test1() throws Exception {
		Reader r = new StringReader("@");
		Scanner s = new Scanner(r);
        assertThrows(LexicalException.class, ()->{
		   s.getNext();
        });
	}
	
	/**
	 * Example showing how to read the input from a file.  Otherwise it is the same as test1.
	 *
	 */
	//@Test
	void test2() throws Exception {
		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
        assertThrows(LexicalException.class, ()->{
		   s.getNext();
        });
	}
	

	
	/**
	 * Another example.  This test case will fail with the provided code, but should pass in your completed Scanner.
	 * @throws Exception
	 */
	@Test
	void test3() throws Exception {
		Reader r = new StringReader(",,::==");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,COMMA);
		assertEquals(t.text,",");
		show(t = s.getNext());
		assertEquals(t.kind,COMMA);
		assertEquals(t.text,",");
		
		show(t = s.getNext());
		assertEquals(t.kind,COLONCOLON);
		assertEquals(t.text,"::");
		
		show(t = s.getNext());
		assertEquals(t.kind,REL_EQEQ);
		assertEquals(t.text,"==");
	}
	
	/**
	 * Another example.  This test case will fail with the provided code, but should pass in your completed Scanner.
	 * @throws Exception
	 */
	@Test
	void test4() throws Exception {
		Reader r = new StringReader("ass");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"ass");
		show(t= s.getNext());
		assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
		show(t= s.getNext());
		assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
		
	}
	
	@Test
	void test5() throws Exception {
		Reader r = new StringReader("123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
	
	}
	
	@Test
	void test6() throws Exception {
		Reader r = new StringReader("abc+");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_PLUS);
		assertEquals(t.text,"+");
		
	}
	
	@Test
	void test7() throws Exception {
		Reader r = new StringReader("abc+123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_PLUS);
		assertEquals(t.text,"+");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
	}
	
	@Test
	void test8() throws Exception {
		Reader r = new StringReader("abc-123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_MINUS);
		assertEquals(t.text,"-");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	
	@Test
	void test9() throws Exception {
		Reader r = new StringReader("abc*123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_TIMES);
		assertEquals(t.text,"*");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	
	@Test
	void test10() throws Exception {
		Reader r = new StringReader("abc/123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_DIV);
		assertEquals(t.text,"/");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	
	@Test
	void test11() throws Exception {
		Reader r = new StringReader("abc%123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_MOD);
		assertEquals(t.text,"%");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	
	@Test
	void test12() throws Exception {
		Reader r = new StringReader("abc^123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_POW);
		assertEquals(t.text,"^");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test13() throws Exception {
		Reader r = new StringReader("abc#123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_HASH);
		assertEquals(t.text,"#");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test14() throws Exception {
		Reader r = new StringReader("abc&123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,BIT_AMP);
		assertEquals(t.text,"&");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	
	@Test
	void test15() throws Exception {
		Reader r = new StringReader("abc~123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,BIT_XOR);
		assertEquals(t.text,"~");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test16() throws Exception {
		Reader r = new StringReader("abc|123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,BIT_OR);
		assertEquals(t.text,"|");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test17() throws Exception {
		Reader r = new StringReader("abc:123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,COLON);
		assertEquals(t.text,":");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test18() throws Exception {
		Reader r = new StringReader("abc//123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,OP_DIVDIV);
		assertEquals(t.text,"//");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test19() throws Exception {
		Reader r = new StringReader("abc<<123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,BIT_SHIFTL);
		assertEquals(t.text,"<<");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test20() throws Exception {
		Reader r = new StringReader("abc<=123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,REL_LE);
		assertEquals(t.text,"<=");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test21() throws Exception {
		Reader r = new StringReader("abc<123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,REL_LT);
		assertEquals(t.text,"<");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test22() throws Exception {
		Reader r = new StringReader("abc>>123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,BIT_SHIFTR);
		assertEquals(t.text,">>");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test23() throws Exception {
		Reader r = new StringReader("abc>=123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,REL_GE);
		assertEquals(t.text,">=");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test25() throws Exception {
		Reader r = new StringReader("abc>123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,REL_GT);
		assertEquals(t.text,">");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test26() throws Exception {
		Reader r = new StringReader("abc=123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,ASSIGN);
		assertEquals(t.text,"=");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test27() throws Exception {
		Reader r = new StringReader("abc==123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,REL_EQEQ);
		assertEquals(t.text,"==");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	@Test
	void test28() throws Exception {
		Reader r = new StringReader("abc~=123");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,REL_NOTEQ);
		assertEquals(t.text,"~=");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		}
	
	@Test
	void test29() throws Exception {
		Reader r = new StringReader("(abc123)");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,LPAREN);
		assertEquals(t.text,"(");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc123");
		show(t= s.getNext());
		assertEquals(t.kind,RPAREN);
		assertEquals(t.text,")");
		}
	@Test
	void test30() throws Exception {
		Reader r = new StringReader("{abc123}");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,LCURLY);
		assertEquals(t.text,"{");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc123");
		show(t= s.getNext());
		assertEquals(t.kind,RCURLY);
		assertEquals(t.text,"}");
		}
	@Test
	void test31() throws Exception {
		Reader r = new StringReader("[abc123]");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,LSQUARE);
		assertEquals(t.text,"[");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc123");
		show(t= s.getNext());
		assertEquals(t.kind,RSQUARE);
		assertEquals(t.text,"]");
		}
	@Test
	void test32() throws Exception {
		Reader r = new StringReader("[{()+()}]");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,LSQUARE);
		assertEquals(t.text,"[");
		show(t= s.getNext());
		assertEquals(t.kind,LCURLY);
		assertEquals(t.text,"{");
		show(t= s.getNext());
		assertEquals(t.kind,LPAREN);
		assertEquals(t.text,"(");
		show(t= s.getNext());
		assertEquals(t.kind,RPAREN);
		assertEquals(t.text,")");
		show(t= s.getNext());
		assertEquals(t.kind,OP_PLUS);
		assertEquals(t.text,"+");
		show(t= s.getNext());
		assertEquals(t.kind,LPAREN);
		assertEquals(t.text,"(");
		show(t= s.getNext());
		assertEquals(t.kind,RPAREN);
		assertEquals(t.text,")");
		show(t= s.getNext());
		assertEquals(t.kind,RCURLY);
		assertEquals(t.text,"}");
		show(t= s.getNext());
		assertEquals(t.kind,RSQUARE);
		assertEquals(t.text,"]");
		}
	@Test
	void test33() throws Exception {
		Reader r = new StringReader("123+321");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"123");
		show(t= s.getNext());
		assertEquals(t.kind,OP_PLUS);
		assertEquals(t.text,"+");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"321");
		}
	@Test
	void test34() throws Exception {
		Reader r = new StringReader("abc..def");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,DOTDOT);
		assertEquals(t.text,"..");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"def");
		}
	@Test
	void test35() throws Exception {
		Reader r = new StringReader("abc..def");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,DOTDOT);
		assertEquals(t.text,"..");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"def");
		}
	@Test
	void test36() throws Exception {
		Reader r = new StringReader("abc...def");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,DOTDOTDOT);
		assertEquals(t.text,"...");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"def");
		}
	@Test
	void test37() throws Exception {
		Reader r = new StringReader("abc...def;a");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t= s.getNext());
		assertEquals(t.kind,DOTDOTDOT);
		assertEquals(t.text,"...");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"def");
		show(t= s.getNext());
		assertEquals(t.kind,SEMI);
		assertEquals(t.text,";");
		show(t= s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"a");
		}
	
	//@Test
	void test38() throws Exception{
		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
        show(t=s.getNext());
        assertEquals(t.kind,NAME);
		assertEquals(t.text,"a");
		show(t=s.getNext());
        assertEquals(t.kind,NAME);
		assertEquals(t.text,"b");
		}
	//@Test
	void test39() throws Exception{ //"abc
			String file = "testInputFiles\\test2.input"; 
			Reader r = new BufferedReader(new FileReader(file));
			Scanner s = new Scanner(r);
			Token t;
	       /* show(t=s.getNext());
	        assertEquals(t.kind,STRINGLIT);
			assertEquals(t.text,"abc");*/
			 assertThrows(LexicalException.class, ()->{
				   s.getNext();
		        });
	}
	//@Test
	void test40() throws Exception{ //abc\ndef
			String file = "testInputFiles\\test2.input"; 
			Reader r = new BufferedReader(new FileReader(file));
			Scanner s = new Scanner(r);
			Token t;
	        show(t=s.getNext());
	        assertEquals(t.kind,NAME);
			assertEquals(t.text,"abc\\v");
			show(t=s.getNext());
	        assertEquals(t.kind,NAME);
			assertEquals(t.text,"def");
			/* assertThrows(LexicalException.class, ()->{
				   s.getNext();
		        });*/
	}
	@Test
	void test41() throws Exception{

		Reader r = new StringReader("\\v");
		Scanner s = new Scanner(r);
		Token t;
		assertThrows(LexicalException.class, ()->{
			   s.getNext();
	        });
	}
	
	@Test
	void test42() throws Exception{

		Reader r = new StringReader("if\\n");
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
        assertEquals(t.kind,KW_if);
		assertEquals(t.text,"if");
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
	}
	@Test
	void test43() throws Exception{

		Reader r = new StringReader("abc\\ndef");
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
        assertEquals(t.kind,NAME);
		assertEquals(t.text,"abc");
		show(t=s.getNext());
        assertEquals(t.kind,NAME);
		assertEquals(t.text,"def");
	}
	@Test
	void testadd() throws Exception{
		Reader r = new StringReader("1 + 2");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"1");
		show(t= s.getNext());
		assertEquals(t.kind,OP_PLUS);
		assertEquals(t.text,"+");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"2");
	}
	@Test
	void testunary1() throws Exception{
		Reader r = new StringReader("-*2\n");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,OP_MINUS);
		assertEquals(t.text,"-");
		show(t= s.getNext());
		assertEquals(t.kind,OP_TIMES);
		assertEquals(t.text,"*");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"2");
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
	}
	
	@Test
	void testfunc() throws Exception{
		Reader r = new StringReader("function(1, 2) end");
		Scanner s = new Scanner(r);
		Token t;
		show(t= s.getNext());
		assertEquals(t.kind,KW_function);
		assertEquals(t.text,"function");
		show(t= s.getNext());
		assertEquals(t.kind,LPAREN);
		assertEquals(t.text,"(");
		show(t= s.getNext());
		assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"1");
		show(t=s.getNext());
        assertEquals(t.kind,COMMA);
		assertEquals(t.text,",");
		show(t=s.getNext());
        assertEquals(t.kind,INTLIT);
		assertEquals(t.text,"2");
		show(t=s.getNext());
        assertEquals(t.kind,RPAREN);
		assertEquals(t.text,")");
		show(t=s.getNext());
        assertEquals(t.kind,KW_end);
		assertEquals(t.text,"end");
		
	}
	
	//@Test
	void test44() throws Exception{	// "a\nb" and 'a\nb'

		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
        show(t=s.getNext());
        assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"a\nb");
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
	}
	
	//@Test
	void test45() throws Exception{	// "a\ab"

		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
        show(t=s.getNext());
        assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"a\\ab");
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
	}
	
	@Test
	void test46() throws Exception{

		Reader r = new StringReader("--abc");
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
	}
	@Test
	void test47() throws Exception{

		Reader r = new StringReader("--abc\\ndef");
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
        assertEquals(t.kind,NAME);
		assertEquals(t.text,"def");
		show(t=s.getNext());
        assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
	}
	
	//@Test 
		void test48() throws Exception{ //-2

			String file = "testInputFiles\\test2.input"; 
			Reader r = new BufferedReader(new FileReader(file));
			Scanner s = new Scanner(r);
			Token t;
			show(t=s.getNext());
			assertEquals(t.kind,OP_MINUS);
			assertEquals(t.text,"-");
			show(t=s.getNext());
			assertEquals(t.kind,INTLIT);
			assertEquals(t.text,"2");
			show(t=s.getNext());
			assertEquals(t.kind,EOF);
			assertEquals(t.text,"EOF");
		}
	
	//@Test
	void test49() throws Exception{	// "a\nb" and 'a\nb'

		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
        show(t=s.getNext());
        assertEquals(t.kind,NAME);
		assertEquals(t.text,"a");
		show(t=s.getNext());
        assertEquals(t.kind,ASSIGN);
		assertEquals(t.text,"=");
		show(t=s.getNext());
        assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"v");
		show(t=s.getNext());
	}
	
	//@Test 
		void test50() throws Exception{ //-*2

			String file = "testInputFiles\\test2.input"; 
			Reader r = new BufferedReader(new FileReader(file));
			Scanner s = new Scanner(r);
			Token t;
			show(t=s.getNext());
			assertEquals(t.kind,OP_MINUS);
			assertEquals(t.text,"-");
			show(t=s.getNext());
			assertEquals(t.kind,OP_TIMES);
			assertEquals(t.text,"*");
			show(t=s.getNext());
			assertEquals(t.kind,INTLIT);
			assertEquals(t.text,"2");
			show(t=s.getNext());
			assertEquals(t.kind,EOF);
			assertEquals(t.text,"EOF");
		}
		//@Test 
		void test51() throws Exception{ //"string"

		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
		assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"\"string\"");
		show(t=s.getNext());
		assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
				}
		//@Test 
		void test52() throws Exception{ //"a"|"b"

		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
		assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"\"a\"");
		show(t=s.getNext());
		assertEquals(t.kind,BIT_OR);
		assertEquals(t.text,"|");
		show(t=s.getNext());
		assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"\"b\"");
		show(t=s.getNext());
		assertEquals(t.kind,EOF);
		assertEquals(t.text,"EOF");
		
				}
		@Test 
		void test53() throws Exception{ //"concat"..

		String file = "testInputFiles\\test2.input"; 
		Reader r = new BufferedReader(new FileReader(file));
		Scanner s = new Scanner(r);
		Token t;
		show(t=s.getNext());
		assertEquals(t.kind,STRINGLIT);
		assertEquals(t.text,"\"concat\"");
		show(t=s.getNext());
		assertEquals(t.kind,DOTDOT);
		assertEquals(t.text,"..");
		show(t=s.getNext());
		assertEquals(t.kind,EOF);
		/*assertEquals(t.text,"+");
		show(t=s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"b");
		show(t=s.getNext());
		assertEquals(t.kind,RPAREN);
		assertEquals(t.text,")");
		show(t=s.getNext());
		assertEquals(t.kind,OP_TIMES);
		assertEquals(t.text,"*");
		show(t=s.getNext());
		assertEquals(t.kind,NAME);
		assertEquals(t.text,"c");
		*/
				}
}
