package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.ClockUpAction;

public class Kabuto_ClockUp_SoundsAndAction extends AbstractGameEffect {
	private boolean sounds = true;
	private boolean action = true;
	private AbstractCard c;
	
	public Kabuto_ClockUp_SoundsAndAction(AbstractCard c) {
		this.c = c;
		this.duration = 4F;//倒数时间
		this.startingDuration = 4F;//持续时间
		this.color = Color.WHITE.cpy();
		
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 2.0F) {
			if(sounds) {
			CardCrawlGame.sound.playA("kabuto_clockup", 0.0f);
			AbstractDungeon.actionManager.addToBottom(new ClockUpAction(c));
			sounds = false;
			}
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		if(action) {
			CardCrawlGame.sound.playA("attackride", 0.0f);
			if (AbstractDungeon.player.discardPile.size() > 0) {
				   AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
				   AbstractDungeon.actionManager.addToBottom(new ShuffleAction(AbstractDungeon.player.drawPile, false));
			}  
			action = false;
		}
	}

	public void dispose() {
	}
}