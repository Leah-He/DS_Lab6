import java.util.Stack;
import javafx.scene.paint.Color;


public class PaintBrush
{

	
	Paint paint;
	
	
	enum BrushMode{
		paintMode,
		fillMode,
		pattern1Mode,
		pattern2Mode
	}

	BrushMode mode;
	
	Paint Gold = new PaintColor(Color.GOLD);
	Paint White = new PaintColor(Color.WHITE);
	
	
/**
set the "paint" for the paintbrush
*/	
	public void setPaint(Paint paint)
	{
		this.paint = paint;
   
   
	}


/*
   gets the present paint on the paint brush
*/
	public Paint getPaint()
	{
		return this.paint;
		//return Gold;
	}
	
   
   /*
   makes the paint on the paint brush a brigter shade.
   */
	public void setBrighter()
	{
		if (this.paint != null) { //maybe check if there is current color first will be better
            this.paint = new PaintBrighter(this.paint);  // make the current paint brighter
        }

	}


   /*
      makes the paint on the paintbrush a darker shade
   */
	public void setDarker()
	{
		if (this.paint != null) {
			this.paint = new PaintDarker(this.paint);  // Make the current paint darker
		}
	}


   /*
      paints the mesh, using the current paint and mode at point x,y
   */
	public void paint(int x, int y, Paint[][] mesh)
	{
		//if use switch, 
		//use break after that, or it will continue to do the other things after.
		//But in the future, 
		//maybe should try to avoid to use the switch 
		//cuz it may lead other problem.
		switch (mode) {
		case paintMode:
			mesh[x][y] = this.paint;
			break;
		case fillMode:
			fillPaint(x,y,mesh, mesh[x][y]);
			break;
		case pattern1Mode:
			pattern1Paint(x, y, mesh, mesh[x][y]);
			break;
		case pattern2Mode:
			pattern2Paint(x, y, mesh, mesh[x][y]);//maybe do something in the future
			break;
		default:
			break;
			
		/*//mesh[x][y] = this.paint;
		if (x < 0 || x >= mesh.length || y < 0 || y >= mesh[0].length) {
			return;
		}
		if (mode == BrushMode.paintMode) {
			mesh[x][y] = this.paint;
		//} else if (mode == BrushMode.fillMode) {
			//fillPaint(x,y,mesh,mesh[x][y], this.paint);
		//} else if (mode == BrushMode.fillMode) {
		*/
		
		}
	}

	private void fillPaint(int x, int y, Paint[][] mesh, Paint ogPaint)
	{
		if (x < 0 || x >= mesh.length || y < 0 || y >= mesh[0].length) {
			return;
		}
		//if (!mesh[x][y].equals(ogPaint)) {
		//return;
		//document version
		if (mesh[x][y].equals(ogPaint)) {
			mesh[x][y] = this.paint;
		} else {
			return;
		}
		
		fillPaint(x+1, y, mesh, ogPaint);
		fillPaint(x-1, y, mesh, ogPaint);
		fillPaint(x, y+1, mesh, ogPaint);
		fillPaint(x, y-1, mesh, ogPaint);				
	}
	
	private void pattern1Paint(int x, int y, Paint[][] mesh, Paint ogPaint)
	{
		if (x < 0 || x >= mesh.length || y < 0 || y >= mesh[0].length) {
			return;
		}
		if (!mesh[x][y].equals(ogPaint)) {
			return;
		}
		if (x % 2 == 0) {//even
			mesh[x][y] = White;
		} else {//odd
			mesh[x][y] = Gold;
		}
		
		pattern1Paint(x-1, y, mesh, ogPaint);
		pattern1Paint(x+1, y, mesh, ogPaint);
		pattern1Paint(x, y-1, mesh, ogPaint);
		pattern1Paint(x, y+1, mesh, ogPaint);
	}
	
	private void pattern2Paint(int x, int y, Paint[][] mesh, Paint ogPaint)
	{
		//we can do something in the future
	}
	
/*
   set the drawing mode of the paint brush.
*/
	public void pointMode()
	{
		mode= BrushMode.paintMode;
	}

	public void fillMode()
	{
		mode = BrushMode.fillMode;
	}

	public void pattern1Mode()
	{
		mode = BrushMode.pattern1Mode;
	}

	public void pattern2Mode()
	{
		mode = BrushMode.pattern2Mode;
	}

}
 