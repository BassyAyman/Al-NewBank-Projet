import { Body, Controller, Get, Post, HttpStatus } from '@nestjs/common';
import { AppService } from './app.service';
import { Transaction } from './Transaction';
import { ApiBody } from "@nestjs/swagger";

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Post()
  createTransaction(@Body() transaction: Transaction) {
    console.log(transaction);
    return {
      status: 'Transaction created',
      statusCode: HttpStatus.CREATED,
    };
  }
}
