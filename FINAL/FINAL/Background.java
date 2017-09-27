class Background
{
	int background_x;
	int backgroundRightEdge;
	int i;
	Background()
	{
		i = 0;
		background_x = 0;
	}
	Background(Background that)
	{
		this.backgroundRightEdge = that.backgroundRightEdge;
		this.background_x = that.background_x;
		this.i = that.i;
	}

	public void update()
	{
		backgroundRightEdge = background_x + 5000;
		//++i;
		//if(i == 10)
			--background_x;
		//if(i == 11)
			i = 0;
	}
}