import { IsNotEmpty, IsString, IsNumber, IsDate } from 'class-validator';

export class Transaction {
  @IsNotEmpty()
  @IsString()
  cardId: string;

  @IsNotEmpty()
  @IsNumber()
  amount: number;

  @IsNotEmpty()
  @IsDate()
  date: Date;
}
