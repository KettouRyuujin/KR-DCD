package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Characters.Decade;

public class Axel_faiztoaxel extends AbstractGameEffect {

	private Texture img = null;
	private boolean Timer = true;
	private boolean start = true;
	
	public Axel_faiztoaxel() {

		this.duration = 5.7F;//倒数时间
		this.startingDuration = 5.7F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 0.35F) {
			if(Timer) {
				EnterButtonAction.AxelForm = true;
				EnterButtonAction.FaizPhone = false;
				EnterButtonAction.FaizPointer = true;
				EnterButtonAction.FaizShot = true;
				EnterButtonAction.FaizEdge = true;
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_Axel_Timer(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.0F));
				Timer = false;
			}
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(40);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(39);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}