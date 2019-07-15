package dcdmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.DragShieldPower;
import dcdmod.Vfx.DragShield_sounds;
import dcdmod.Vfx.Ryuki_guard;



public class DragShield extends AbstractCustomCardWithType{
	
	public static final String ID = "DragShield";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/DragShield.png";
	private static final int COST = 1;
	private static final int MAGIC_NUM = 2;
	boolean change = true;
	private List<TooltipInfo> tips;
	
	public DragShield() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Ryuki);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragShieldPower(p, this.magicNumber), this.magicNumber));
		if(!p.hasPower("DragShieldPower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guard(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.8F));
		}
		AbstractDungeon.actionManager.addToTop(new VFXAction(new DragShield_sounds(p.drawX - 200.00f, p.drawY + 250.00f), 1.5F));
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new DragShield();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
	public void optionDecade() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionKuuga() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionAgito() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionRyuki() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 2;
		}
	}

	@Override
	public void optionFaiz() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionBlade() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionHibiki() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionKabuto() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionDenO() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionKiva() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionNeutral() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			this.upgradeMagicNumber(1);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("DragShield");
        NAME = DragShield.cardStrings.NAME;
        DESCRIPTION = DragShield.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DragShield.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.exhaust && change){
			this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[0];
			initializeDescription();
			change = false;
		}
		if(!this.exhaust) {
			change = true;
		}
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
