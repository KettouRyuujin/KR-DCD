package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Actions.GetHibikiAttackCardAciton;
import dcdmod.Actions.ReturnRandomNumberAction2;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.HibikiBurnPower;
import dcdmod.Vfx.Hibiki_Attack2_sounds;
import dcdmod.Vfx.Hibiki_attack2;



public class Hibiki_Attack2 extends AbstractCustomCardWithType{
	
	public static final String ID = "Hibiki_Attack2";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Hibiki_Attack2.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private List<TooltipInfo> tips;
	
	public Hibiki_Attack2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Hibiki);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;
		this.damageType = DamageType.NORMAL;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		int d = this.damage/2;
		if(p.hasPower("HibikiKurenaiPower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_attack2(m, this.damage, this.damageType, this.magicNumber), 0.0F));
			if(50 > ReturnRandomNumberAction2.ReturnRandomNumber()) {
				AbstractDungeon.actionManager.addToBottom(new GetHibikiAttackCardAciton());
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new HibikiBurnPower(m, this.magicNumber, p), this.magicNumber));
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				 if ((!monster.isDead) && (!monster.isDying)) {
					 if(monster != m) {
						 AbstractDungeon.actionManager.addToTop(new DamageAction(monster,new DamageInfo(p, d, this.damageType), AbstractGameAction.AttackEffect.FIRE));
						 AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(monster, p, new HibikiBurnPower(monster, this.magicNumber, p), this.magicNumber));
					 }
				 }
			}
		}
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_Attack2_sounds(p.drawX - 200.00f, p.drawY + 250.00f), 1.5F));
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
 			this.damage += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
 			this.isDamageModified = true;
 		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Hibiki_Attack2();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
	public void optionDecade() {

	}

	@Override
	public void optionKuuga() {

	}

	@Override
	public void optionAgito() {
	}

	@Override
	public void optionRyuki() {
	}

	@Override
	public void optionFaiz() {
	}

	@Override
	public void optionBlade() {
	}

	@Override
	public void optionHibiki() {
	}

	@Override
	public void optionKabuto() {
	}

	@Override
	public void optionDenO() {
	}

	@Override
	public void optionKiva() {
	}

	@Override
	public void optionNeutral() {

	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Attack2");
        NAME = Hibiki_Attack2.cardStrings.NAME;
        DESCRIPTION = Hibiki_Attack2.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Hibiki_Attack2.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
		}
	}

	
	
}
