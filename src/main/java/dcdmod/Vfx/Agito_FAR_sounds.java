package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Agito_FAR_sounds extends AbstractGameEffect {

	private boolean sounds = true;
	
	public Agito_FAR_sounds() {
		this.duration = 4F;//倒数时间
		this.startingDuration = 4F;//持续时间
		
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
			 if(AbstractDungeon.player.hasPower("AgitoStormPower")) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(), 0F));
			}
			else if(AbstractDungeon.player.hasPower("AgitoStormPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_storm(), 0F));
			}
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}