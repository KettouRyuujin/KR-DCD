package dcdmod.Card.Uncommon;

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
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.HibikiBurnPower;
import dcdmod.Vfx.Hibiki_attack3;



public class Hibiki_Attack3 extends AbstractCustomCardWithType{
	
	public static final String ID = "Hibiki_Attack3";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Hibiki_Attack3.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 12;
	private List<TooltipInfo> tips;
	
	public Hibiki_Attack3() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Hibiki);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 3;
		this.damageType = DamageType.NORMAL;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		if(p.hasPower("HibikiKurenaiPower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_attack3(m, this.damage, this.damageType, this.magicNumber), 0.0F));
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new HibikiBurnPower(m, this.magicNumber, p), this.magicNumber));
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
 			this.damage += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
 			this.isDamageModified = true;
 		}
		if(arg0.hasPower("HibikiBurnPower")) {
			if(AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
				int x = arg0.getPower("HibikiBurnPower").amount/5;
				if(x <= 0) {
					x = 1;
				}
				this.damage += this.damage * x;
				this.isDamageModified = true;
			}
			else {
				this.damage += this.damage;
				this.isDamageModified = true;
			}
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Hibiki_Attack3();
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
            this.upgradeDamage(3);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Attack3");
        NAME = Hibiki_Attack3.cardStrings.NAME;
        DESCRIPTION = Hibiki_Attack3.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Hibiki_Attack3.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
