package dcdmod.Card.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class UnarmedAttack5 extends AbstractCustomCardWithType{
	
	public static final String ID = "UnarmedAttack5";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/UnarmedAttack5.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	
	
	public UnarmedAttack5() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = 3;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage,this.damageType), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("Dexterity") && AbstractDungeon.player.getPower("Dexterity").amount>0) {
			if(upgraded) {
				this.damage += (AbstractDungeon.player.getPower("Dexterity").amount * 4);
			}
			else {
				this.damage += (AbstractDungeon.player.getPower("Dexterity").amount * 3);
			}
			this.isDamageModified = true;
		}
		if(AbstractDungeon.player.hasPower("BladeBeatPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeBeatPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new UnarmedAttack5();
    }
	
	@Override
	public void optionDecade() {
		this.damageType = DamageType.NORMAL;
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}

	@Override
	public void optionKuuga() {
		if(AbstractDungeon.player.hasPower("KuugaPegasusPower")||AbstractDungeon.player.hasPower("RisingPegasusPower")) {
			this.damageType = DamageType.HP_LOSS;
		}
		setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
	}

	@Override
	public void optionAgito() {
	    setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
	}

	@Override
	public void optionRyuki() {
	    setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
	}

	@Override
	public void optionFaiz() {
	    setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
	}

	@Override
	public void optionBlade() {
	    setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
	}

	@Override
	public void optionHibiki() {
	    setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
	}

	@Override
	public void optionKabuto() {
	    setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
	}

	@Override
	public void optionNeutral() {
		this.damageType = DamageType.NORMAL;
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("UnarmedAttack5");
        NAME = UnarmedAttack5.cardStrings.NAME;
        DESCRIPTION = UnarmedAttack5.cardStrings.DESCRIPTION;
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
