package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.badlogic.gdx.graphics.Color;

public class Agito_FAR_sounds extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean sounds = true;
	
	public Agito_FAR_sounds(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 4F;//倒数时间
		this.startingDuration = 4F;//持续时间
		this.color = Color.WHITE.cpy();
		
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.0F) {
			if(sounds) {
			CardCrawlGame.sound.playA("FAR_AGITO", 0.0f);
			CardCrawlGame.sound.playA("agito_BGM1", 0.0f);
			sounds = false;
			}
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			if(AbstractDungeon.player.hasPower("AgitoStormPower")&&AbstractDungeon.player.hasPower("AgitoStormPower")) {
			}
			else if(AbstractDungeon.player.hasPower("AgitoStormPower")) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0F));
			}
			else if(AbstractDungeon.player.hasPower("AgitoStormPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_storm(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0F));
			}
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}