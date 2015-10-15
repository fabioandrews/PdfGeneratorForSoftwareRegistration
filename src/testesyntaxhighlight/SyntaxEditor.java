package testesyntaxhighlight;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class SyntaxEditor extends JFrame implements ActionListener
{
	private RSyntaxTextArea text = new RSyntaxTextArea();
	
	private JMenu fileMenu = new JMenu( "File" );
	private JMenuItem fileOpen = new JMenuItem( "Open" );
	private JMenuItem fileExit = new JMenuItem( "Exit" );
	
	private Map<String,String> extensionMap = new HashMap<String,String>();
	
	public SyntaxEditor( String[] args )
	{
		super( "Syntax Text Editor" );
		
		// Build the extension Map
		extensionMap.put( "java", SyntaxConstants.SYNTAX_STYLE_JAVA );
		extensionMap.put( "xml", SyntaxConstants.SYNTAX_STYLE_XML );
		extensionMap.put( "js", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT );
		extensionMap.put( "html", SyntaxConstants.SYNTAX_STYLE_HTML );
		extensionMap.put( "c", SyntaxConstants.SYNTAX_STYLE_C );
		extensionMap.put( "cpp", SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS );
		extensionMap.put( "groovy", SyntaxConstants.SYNTAX_STYLE_GROOVY );
		extensionMap.put( "jsp", SyntaxConstants.SYNTAX_STYLE_JSP );
		extensionMap.put( "properties", SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE );
		extensionMap.put( "sql", SyntaxConstants.SYNTAX_STYLE_SQL );
		extensionMap.put( "sh", SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL );
		extensionMap.put( "bat", SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH );
		
		// Build a simple menu
		JMenuBar menubar = new JMenuBar();
		fileMenu.add( fileOpen );
		fileMenu.add( fileExit );
		menubar.add( fileMenu );
		add( menubar, BorderLayout.NORTH );
		
		// Add ourself as an action listener (don't do this in a production app,
		// instead use Actions
		fileOpen.addActionListener( this );
		fileExit.addActionListener( this );
		
		// Add our text control to the center of the Frame's border layout
		add( new RTextScrollPane( text ) );
		
		if( args.length > 0 )
		{
			openFile( args[ 0 ] );
		}
		
		// Show our window
		setSize( 1024, 768 );
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation( d.width/2 - 512, d.height/2 - 384 );
		setVisible( true );
		
		// Close our frame when the user closes it
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void openFile( String filename )
	{
		try
		{
			BufferedReader reader = new BufferedReader( new FileReader( filename ) );
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while( line != null )
			{
				sb.append( line + "\n" );
				line = reader.readLine();
			}
			
			// Put the text into the RSyntaxTextArea
			text.setText( sb.toString() );
			
			// See if we can set its highlighting
			if( filename.indexOf( '.' ) != -1 )
			{
				String extension = filename.substring( filename.lastIndexOf( '.' ) + 1 );
				if( extensionMap.containsKey( extension.toLowerCase() ) )
				{
					text.setSyntaxEditingStyle( extensionMap.get( extension.toLowerCase() ) );
					
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main( String[] args )
	{
		AppStarter appstarter = new AppStarter( args );
        SwingUtilities.invokeLater( appstarter );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == fileOpen )
		{
			JFileChooser chooser = new JFileChooser();
	    	if( chooser.showOpenDialog( this )== JFileChooser.APPROVE_OPTION )
	    	{
	    		// The user chose OK, so get the filename and the absolute filename
	    		openFile( chooser.getSelectedFile().getAbsolutePath() );
	    	}
		}
		else if( e.getSource() == fileExit )
		{
			Printable printear = text.getPrintable(new MessageFormat("Printeat"), new MessageFormat("footer"));
			Printable printearClone = printear;
			byte [] textoPraImprimir = PdfPrinter.printToPdf(printear, printearClone, PageFormat.PORTRAIT);
		}
	}

}

class AppStarter extends Thread
{
	private String[] args;
	
	public AppStarter( String[] args )
	{
		this.args = args;
	}
	
	public void run()
	{
		SyntaxEditor editor = new SyntaxEditor( args );
	}
}