#!/usr/local/bin/perl
use diagnostics;
use strict;
use warnings;
use Digest::MD5;
 
while (<>) {
    chomp;
    if (-f) {
        open INFILE, $_;
        my $context = new Digest::MD5;
        $context->addfile(*INFILE);
        my $digest = $context->hexdigest;
        close INFILE;
        print "$digest  $_\n";
    }
}
