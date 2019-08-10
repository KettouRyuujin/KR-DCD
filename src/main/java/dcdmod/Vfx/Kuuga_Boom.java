package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_Boom extends AbstractGameEffect {

	private static boolean again;
	private boolean start = true;
	private AbstractCreature target;


	public Kuuga_Boom(AbstractCreature target, boolean again) {
		this.duration = 0.96F;//倒数时间
		this.startingDuration = 0.96F;//持续时间
		this.target = target;
		Kuuga_Boom.again = again;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR/Kuuga_Boom.atlas";
			String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR/Kuuga_Boom.json";
			new AbstractAnimation("kuuga_boom", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8f, this.target.drawX, this.target.drawY + this.target.hb_h/2, this.target.hb_w, this.target.hb_h, 1.0f);
			AbstractAnimation kuuga_attacked =  AbstractAnimation.getAnimation("kuuga_boom");
			kuuga_attacked.setMovable(false);
			kuuga_attacked.state.setAnimation(0, "boom", false);
			CardCrawlGame.sound.playA("kuuga_boom", 0.0f);
			again = false;
			start = false;
		}

		if (this.duration < 0.0F) {
			if(!again){
				AbstractAnimation.clear("kuuga_boom");
			}
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}