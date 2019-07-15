package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import com.badlogic.gdx.graphics.Color;

public class Axel_SparkleCut extends AbstractGameEffect {
	
	private float x;
	private float y;
	private Texture img = null;
	boolean Start= true;
	boolean Final = false;
	int n = 0;
	

	
	
	public Axel_SparkleCut(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}
		this.x = x;
		this.y = y;
		this.duration = 30.0F;//倒数时间
		this.startingDuration = 30.0F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(!EnterButtonAction.AxelForm) {
			if(Start) {
				this.img =new Texture(Gdx.files.internal("img/char/DCD_Animational/faiz_Axel/SparkleCut.png"));
				this.x = x- (float)this.img.getWidth() / 2.0F;
				for(int i=0;i<EnterButtonAction.EdgePoint;i++) {
					if(n != EnterButtonAction.EdgePoint) {
						n +=1;
					}	
				}
				Start = false;
				Final = true;
			}

		}
		if(Final) {
			if(n == EnterButtonAction.EdgePoint&&this.duration<(30.0f-(EnterButtonAction.EdgePoint*2.0f))) {
				this.isDone = true;
			}
		}
		if (this.duration < 0.0F) {
			this.isDone = true;

		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}