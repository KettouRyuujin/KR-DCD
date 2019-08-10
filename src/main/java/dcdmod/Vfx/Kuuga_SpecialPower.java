package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_SpecialPower extends AbstractGameEffect {

	private final String id;
	private boolean start = true;
	private AbstractCreature target;
	public static int x;

	public Kuuga_SpecialPower(AbstractCreature target) {
		this.duration = 0.96F;//倒数时间
		this.startingDuration = 0.96F;//持续时间
		this.target = target;
		this.id = "kuuga_SpecialPower" + x;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			x += 1;
			String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR/Kuuga_SpecialPower.atlas";
			String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR/Kuuga_SpecialPower.json";
			new AbstractAnimation(this.id, KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8f, this.target.drawX, this.target.drawY + this.target.hb_h/2, this.target.hb_w, this.target.hb_h, 1.0f);
			AbstractAnimation kuuga_attacked =  AbstractAnimation.getAnimation(this.id);
			kuuga_attacked.setMovable(false);
			kuuga_attacked.state.setAnimation(0, "normal", true);
			start = false;
		}

		if (this.duration < 0.0F) {
			AbstractAnimation.clear(this.id);
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}