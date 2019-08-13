package dcdmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import dcdmod.DCDmod;
import dcdmod.Actions.ReturnRandomNumberAction2;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KuugaSpecialPower;
import dcdmod.Vfx.Kuuga_UnarmedAttack6;


public class UnarmedAttack6 extends AbstractCustomCardWithType{
	
	public static final String ID = "UnarmedAttack6";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/UnarmedAttack6.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;

	public UnarmedAttack6() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.UnarmedCard);
		this.baseDamage = ATTACK_DMG;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("KamenRideKuugaPower")){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_UnarmedAttack6(m,this.damage,this.damageType),1.0F));
			if(p.hasPower("RisingMightyPower")){
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
		if(p.hasPower("Dexterity")) {
			int x = p.getPower("Dexterity").amount;
			AbstractCard c;
			if(upgraded) {
				if(x>=8) {
					x = 8;
				}
				if(ReturnRandomNumberAction2.ReturnRandomNumber()< x * 10 && !m.isDead && !m.isDying) {
					c = this.makeCopy();
					c.freeToPlayOnce = true;
					c.purgeOnUse = true;
    				AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, m, this.energyOnUse));
				}
			}
			else {
				if(x>=16) {
					x = 16;
				}
				if(ReturnRandomNumberAction2.ReturnRandomNumber()< x * 5  && !m.isDead && !m.isDying) {
					c = this.makeCopy();
					c.freeToPlayOnce = true;
					c.purgeOnUse = true;
    				AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, m, this.energyOnUse));
				}
			}
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeBeatPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeBeatPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new UnarmedAttack6();
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
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("UnarmedAttack6");
        NAME = UnarmedAttack6.cardStrings.NAME;
        DESCRIPTION = UnarmedAttack6.cardStrings.DESCRIPTION;
    	UPGRADE_DESCRIPTION = UnarmedAttack6.cardStrings.UPGRADE_DESCRIPTION;
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
